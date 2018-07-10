package com.github.evgeniiavak.simpleforms.controller;

import com.github.evgeniiavak.simpleforms.model.Mood;
import com.github.evgeniiavak.simpleforms.service.MoodQuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController("/forms")
public class FormsController {
    private MoodQuestionsService moodQuestionsService;

    @Autowired
    public FormsController(MoodQuestionsService moodQuestionsService) {
        this.moodQuestionsService = moodQuestionsService;
    }

    @PostMapping("mood")
    public Mood postMoodForm(@RequestBody Mood mood) {
        moodQuestionsService.save(mood);
        return mood;
    }

    @GetMapping("mood/{id}")
    public Mood postMoodForm(@PathVariable UUID id) {
        return moodQuestionsService.get(id);
    }
}
