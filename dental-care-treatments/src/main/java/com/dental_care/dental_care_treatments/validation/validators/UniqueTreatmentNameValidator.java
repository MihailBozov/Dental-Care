package com.dental_care.dental_care_treatments.validation.validators;

import com.dental_care.dental_care_treatments.model.entity.Treatment;
import com.dental_care.dental_care_treatments.repository.TreatmentRepository;
import com.dental_care.dental_care_treatments.validation.UniqueTreatmentName;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class UniqueTreatmentNameValidator implements ConstraintValidator<UniqueTreatmentName, String> {
    
  private TreatmentRepository treatmentRepository;
    
    @Autowired
    public UniqueTreatmentNameValidator(TreatmentRepository treatmentRepository) {
        this.treatmentRepository = treatmentRepository;
    }
    
    @Override
    public void initialize(UniqueTreatmentName constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
    
    @Override
    public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {
        Optional<Treatment> treatmentNameDb = this.treatmentRepository.findByName(name);
        if (treatmentNameDb.isPresent()) {
            return false;
        }
        return true;
    }
}
