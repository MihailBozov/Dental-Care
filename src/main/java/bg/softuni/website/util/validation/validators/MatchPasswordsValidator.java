package bg.softuni.website.util.validation.validators;

import bg.softuni.website.models.dtos.RegisterDto;
import bg.softuni.website.util.validation.MatchPasswords;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MatchPasswordsValidator implements ConstraintValidator<MatchPasswords, RegisterDto> {
    
    @Override
    public void initialize(MatchPasswords constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
    
    @Override
    public boolean isValid(RegisterDto registerDto, ConstraintValidatorContext constraintValidatorContext) {
        String password = registerDto.getPassword();
        String confirmPassword = registerDto.getConfirmPassword();
        
        if (password.isBlank() || confirmPassword.isBlank()) {
            return false;
        }
        return password.equals(confirmPassword);
    }
}
