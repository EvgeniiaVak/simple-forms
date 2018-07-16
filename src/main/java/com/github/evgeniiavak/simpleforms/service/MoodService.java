package com.github.evgeniiavak.simpleforms.service;

import com.github.evgeniiavak.simpleforms.model.Mood;
import com.github.evgeniiavak.simpleforms.exception.NotFoundException;
import com.github.evgeniiavak.simpleforms.repository.MoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MoodService {

    private MoodRepository moodRepository;

    @Autowired
    public MoodService(MoodRepository moodRepository) {
        this.moodRepository = moodRepository;
    }

    public Mood save(Mood mood) {
        return moodRepository.save(mood);
    }

    public Mood get(UUID id) {
        return moodRepository.findById(id).orElse(null);
    }

    public Mood delete(UUID uuid) {
        Mood mood = moodRepository.findById(uuid).orElseThrow(NotFoundException::new);
        moodRepository.delete(mood);
        return mood;
    }
}
