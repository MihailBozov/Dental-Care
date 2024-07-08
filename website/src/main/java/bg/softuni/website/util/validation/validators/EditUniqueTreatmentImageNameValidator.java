package bg.softuni.website.util.validation.validators;

import bg.softuni.website.models.dtos.EditTreatmentDto;
import bg.softuni.website.models.entities.Treatment;
import bg.softuni.website.services.TreatmentService;
import bg.softuni.website.util.validation.EditUniqueTreatmentImageName;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class EditUniqueTreatmentImageNameValidator implements ConstraintValidator<EditUniqueTreatmentImageName, EditTreatmentDto> {
    
    private TreatmentService treatmentService;
    
    @Autowired
    public EditUniqueTreatmentImageNameValidator(TreatmentService treatmentService) {
        this.treatmentService = treatmentService;
    }
    
    @Override
    public boolean isValid(EditTreatmentDto editTreatmentDto, ConstraintValidatorContext constraintValidatorContext) {
        Long id = editTreatmentDto.getId();
        Treatment treatment = this.treatmentService.getTreatment(id);
        
        if (editTreatmentDto.getImage() == null) {
            return true;
        }
        
        List<Treatment> treatments = this.treatmentService.getAllTreatmentsFiltered(id);
        return treatments
                .stream()
                .noneMatch(t -> t.getImage().getName().equals(editTreatmentDto.getImage().getName()));
    }
}
