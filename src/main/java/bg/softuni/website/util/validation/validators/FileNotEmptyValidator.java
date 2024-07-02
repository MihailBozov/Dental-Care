package bg.softuni.website.util.validation.validators;

import bg.softuni.website.util.validation.FileNotEmpty;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;


public class FileNotEmptyValidator implements ConstraintValidator<FileNotEmpty, MultipartFile> {
    @Override
    public void initialize(FileNotEmpty constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
    
    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext constraintValidatorContext) {
        
        if (file == null || file.isEmpty()) {
            return false;
        }
        
        String contentType = file.getContentType();
        
        if (contentType == null || !contentType.startsWith("image/") ) {
            return false;
        }
        
        return true;
        
    }
}