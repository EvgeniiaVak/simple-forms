package com.github.evgeniiavak.simpleforms.controller;


import com.github.evgeniiavak.simpleforms.exception.NotFoundException;
import com.github.evgeniiavak.simpleforms.model.Mood;
import com.github.evgeniiavak.simpleforms.service.MoodService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.Link;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.File;
import java.util.UUID;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.when;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringRunner.class)
@WebMvcTest(MoodController.class)
public class MoodControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MoodService moodService;

    @Before
    public void setUp() {
        RestAssuredMockMvc.mockMvc(mvc);
    }

    @Test
    public void testLinks() {
        Link link = new Link("http://localhost:8080/something");
        assertThat(link.getHref(), is("http://localhost:8080/something"));
        assertThat(link.getRel(), is(Link.REL_SELF));
        System.out.println(link);
    }

    @Test
    public void post() {
        Mood savedMood = getMood();
        Mockito.when(moodService.save(Mockito.any())).thenReturn(savedMood);
        given().
                header("Content-Type", "application/json;charset=UTF-8").
                body(new File("src/test/resources/mood.json")).

        when().
                post("/mood").

        then().
                statusCode(HttpStatus.SC_CREATED).
                header("Content-Type", "application/json;charset=UTF-8").
                header("Location", "http://localhost/mood/" + savedMood.getId()).
                body("id", equalTo(savedMood.getId().toString()));
    }

    @Test
    public void get() {
        Mood savedMood = getMood();
        Mockito.when(moodService.get(savedMood.getId())).thenReturn(savedMood);

        when().
                get("/mood/" + savedMood.getId()).

        then().
                statusCode(HttpStatus.SC_OK).
                header("Content-Type", "application/json;charset=UTF-8").
                body("id", equalTo(savedMood.getId().toString()));
    }

    @Test
    public void testDeleteNotFound() {
        UUID uuid = UUID.randomUUID();
        Mockito.when(moodService.delete(Mockito.any())).thenThrow(new NotFoundException());
        when().
                delete("mood/" + uuid).
        then().
                statusCode(HttpStatus.SC_NOT_FOUND);
    }

    private static Mood getMood() {
        Mood mood = new Mood();
        mood.setId(UUID.randomUUID());
        return mood;
    }
}