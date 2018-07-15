package com.github.evgeniiavak.simpleforms.controller;

import com.github.evgeniiavak.simpleforms.model.Mood;
import com.github.evgeniiavak.simpleforms.service.MoodQuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController("/forms")
public class FormsController {
    private MoodQuestionsService moodQuestionsService;

    @Autowired
    public FormsController(MoodQuestionsService moodQuestionsService) {
        this.moodQuestionsService = moodQuestionsService;
    }

    @PostMapping("mood")
    public ResponseEntity<Mood> post(@RequestBody Mood mood) {
        moodQuestionsService.save(mood);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/")
                .path(mood.getUuid().toString())
                .build().toUri();
        return ResponseEntity.created(uri).body(mood);
    }

    @GetMapping("mood/{uuid}")
    public ResponseEntity<Mood> get(@PathVariable UUID uuid) {
        Mood mood = moodQuestionsService.get(uuid);
        return ResponseEntity.ok(mood);
    }

    @DeleteMapping("mood/{uuid}")
    public ResponseEntity<Mood> delete(@PathVariable UUID uuid) {
        Mood mood = moodQuestionsService.delete(uuid);
        return ResponseEntity.ok(mood);
    }
}
