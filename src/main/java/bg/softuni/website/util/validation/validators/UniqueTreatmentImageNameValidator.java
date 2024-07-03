package bg.softuni.website.util.validation.validators;


import bg.softuni.website.models.entities.Image;
import bg.softuni.website.repositories.ImageRepository;
import bg.softuni.website.util.validation.UniqueTreatmentImageName;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public class UniqueTreatmentImageNameValidator implements ConstraintValidator<UniqueTreatmentImageName, MultipartFile> {
    
    private ImageRepository imageRepository;
    
    @Autowired
    public UniqueTreatmentImageNameValidator(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }
    
    
    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext constraintValidatorContext) {
        
        Optional<Image> image = imageRepository.findByName(file.getOriginalFilename());
        return image.isEmpty();
    }
}
