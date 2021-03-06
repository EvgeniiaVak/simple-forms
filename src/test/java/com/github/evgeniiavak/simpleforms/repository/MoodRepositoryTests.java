package com.github.evgeniiavak.simpleforms.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.evgeniiavak.simpleforms.exception.NotFoundException;
import com.github.evgeniiavak.simpleforms.model.Mood;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import static junit.framework.TestCase.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MoodRepositoryTests {

    private static final String SQL_INSERT =
            "INSERT INTO " +
                    "mood(id, time, joy, focus, anxiety, irritability, sleep, sport, coffee, comment) " +
                    "VALUES (?, now(), 6, 6, 0, 2, 8, 30, 2, 'test comment');";

    @Autowired
    private MoodRepository moodRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Mood mood;

    @Before
    public void init() throws IOException{
        ObjectMapper objectMapper = new ObjectMapper();
        mood = objectMapper.readValue(new File("src/test/resources/mood.json"), Mood.class);
    }

    @Test
    public void testSave()  {
        Mood savedMood = moodRepository.save(mood);

        assertNotNull(savedMood.getId());
    }

    @Test
    public void testFind() {
        UUID uuid = UUID.randomUUID();

        jdbcTemplate.update(SQL_INSERT, uuid);

        Mood foundMood = moodRepository.findById(uuid).orElseThrow(NotFoundException::new);

        assertNotNull(foundMood);
    }

    @Test
    public void testDelete() {
        UUID uuid = UUID.randomUUID();
        mood.setId(uuid);
        int inserted = jdbcTemplate.update(SQL_INSERT, uuid);

        moodRepository.delete(mood);
        Mood found = moodRepository.findById(uuid).orElse(null);

        assertEquals(1, inserted);
        assertNull(found);
    }

    @After
    public void cleanup() {
        jdbcTemplate.update("DELETE FROM mood");
        jdbcTemplate.update("DELETE FROM medicine");
    }
}