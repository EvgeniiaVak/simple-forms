package com.github.evgeniiavak.simpleforms.repository;

import com.github.evgeniiavak.simpleforms.model.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, UUID> {
}
