package com.github.evgeniiavak.simpleforms.repository;

import com.github.evgeniiavak.simpleforms.model.MoodQuestionsForm;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MoodQuestionsRepository extends CrudRepository<MoodQuestionsForm, UUID> {
}
