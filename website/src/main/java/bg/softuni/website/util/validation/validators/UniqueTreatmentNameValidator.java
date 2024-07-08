package bg.softuni.website.util.validation.validators;

import bg.softuni.website.models.entities.Treatment;
import bg.softuni.website.repositories.TeamRepository;
import bg.softuni.website.repositories.TreatmentRepository;
import bg.softuni.website.util.validation.UniqueTreatmentName;
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
