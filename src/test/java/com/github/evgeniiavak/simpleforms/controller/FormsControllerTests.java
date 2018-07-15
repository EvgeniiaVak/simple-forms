package com.github.evgeniiavak.simpleforms.controller;


import com.github.evgeniiavak.simpleforms.exception.NotFoundException;
import com.github.evgeniiavak.simpleforms.model.Mood;
import com.github.evgeniiavak.simpleforms.service.MoodQuestionsService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.File;
import java.util.UUID;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.when;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringRunner.class)
@WebMvcTest(FormsController.class)
public class FormsControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MoodQuestionsService moodQuestionsService;

    @Before
    public void setUp() {
        RestAssuredMockMvc.mockMvc(mvc);
    }

    @Test
    public void post() {
        Mood savedMood = getMood();
        Mockito.when(moodQuestionsService.save(Mockito.any())).thenReturn(savedMood);
        given().
                header("Content-Type", "application/json;charset=UTF-8").
                body(new File("src/test/resources/mood.json")).

        when().
                post("/mood").

        then().
                statusCode(HttpStatus.SC_CREATED).
                header("Content-Type", "application/json;charset=UTF-8").
                header("Location", "http://localhost/mood/" + savedMood.getUuid()).
                body("uuid", equalTo(savedMood.getUuid().toString()));
    }

    @Test
    public void get() {
        Mood savedMood = getMood();
        Mockito.when(moodQuestionsService.get(savedMood.getUuid())).thenReturn(savedMood);

        when().
                get("/mood/" + savedMood.getUuid()).

        then().
                statusCode(HttpStatus.SC_OK).
                header("Content-Type", "application/json;charset=UTF-8").
                body("uuid", equalTo(savedMood.getUuid().toString()));
    }

    @Test
    public void testDeleteNotFound() {
        UUID uuid = UUID.randomUUID();
        Mockito.when(moodQuestionsService.delete(Mockito.any())).thenThrow(new NotFoundException());
        when().
                delete("mood/" + uuid).
        then().
                statusCode(HttpStatus.SC_NOT_FOUND);
    }

    private static Mood getMood() {
        Mood mood = new Mood();
        mood.setUuid(UUID.randomUUID());
        return mood;
    }
}