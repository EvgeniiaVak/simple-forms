package com.github.evgeniiavak.simpleforms;

import com.github.evgeniiavak.simpleforms.controller.MoodController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MoodRegisterApplicationTests {

    @Autowired
    private MoodController controller;

    @Test
    public void contextLoads() {
        assertThat(controller).isNotNull();
    }

}
