package bg.softuni.website.util.validation.validators;

import bg.softuni.website.models.dtos.RegisterDto;
import bg.softuni.website.util.validation.MatchRegisterPasswords;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MatchRegisterPasswordsValidator implements ConstraintValidator<MatchRegisterPasswords, RegisterDto> {
    
    @Override
    public void initialize(MatchRegisterPasswords constraintAnnotation) {
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
