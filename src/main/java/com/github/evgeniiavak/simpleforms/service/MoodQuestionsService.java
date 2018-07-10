package com.github.evgeniiavak.simpleforms.service;

import com.github.evgeniiavak.simpleforms.model.Mood;
import com.github.evgeniiavak.simpleforms.repository.MoodQuestionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MoodQuestionsService {

    private MoodQuestionsRepository moodQuestionsRepository;

    @Autowired
    public MoodQuestionsService(MoodQuestionsRepository moodQuestionsRepository) {
        this.moodQuestionsRepository = moodQuestionsRepository;
    }

    public boolean save(Mood mood) {
        moodQuestionsRepository.save(mood);
        return true;
    }

    public Mood get(UUID id) {
        return moodQuestionsRepository.findById(id).orElse(null);
    }
}
