package com.github.evgeniiavak.simpleforms.controller;

import com.github.evgeniiavak.simpleforms.model.Mood;
import com.github.evgeniiavak.simpleforms.service.MoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@RequestMapping("/mood")
public class MoodController {
    private MoodService moodService;

    @Autowired
    public MoodController(MoodService moodService) {
        this.moodService = moodService;
    }

    public MoodController() {
    }

    @PostMapping
    public ResponseEntity<Resource<Mood>> post(@RequestBody Mood mood) {
        mood = moodService.save(mood);

        Link self = linkTo(MoodController.class).slash(mood).withSelfRel();

        return ResponseEntity.
                status(HttpStatus.CREATED).
                body(new Resource<>(mood, self));
    }

    @GetMapping("{uuid}")
    public ResponseEntity<Mood> get(@PathVariable UUID uuid) {
        Mood mood = moodService.get(uuid);
        return ResponseEntity.ok(mood);
    }

    @DeleteMapping("{uuid}")
    public ResponseEntity<Mood> delete(@PathVariable UUID uuid) {
        Mood mood = moodService.delete(uuid);
        return ResponseEntity.ok(mood);
    }
}
