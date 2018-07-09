package com.github.evgeniiavak.simpleforms.service;

import com.github.evgeniiavak.simpleforms.model.MoodQuestionsForm;
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

    public boolean save(MoodQuestionsForm moodQuestionsForm) {
        moodQuestionsRepository.save(moodQuestionsForm);
        return true;
    }

    public MoodQuestionsForm get(UUID id) {
        return moodQuestionsRepository.findById(id).orElse(null);
    }
}
