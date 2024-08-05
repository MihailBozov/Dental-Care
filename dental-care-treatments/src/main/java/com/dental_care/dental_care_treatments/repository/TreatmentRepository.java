package com.dental_care.dental_care_treatments.repository;

import com.dental_care.dental_care_treatments.model.entity.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TreatmentRepository extends JpaRepository<Treatment, Long> {
    
    Optional<Treatment> findByName(String name);
    
    List<Treatment> findAllByIdNot(Long id);
}
