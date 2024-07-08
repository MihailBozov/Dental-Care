package bg.softuni.website.util.validation.validators;

import bg.softuni.website.models.dtos.ResetPasswordDto;
import bg.softuni.website.util.validation.ResetPasswordsMatch;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ResetPasswordsMatchValidator implements ConstraintValidator<ResetPasswordsMatch, ResetPasswordDto> {
    @Override
    public boolean isValid(ResetPasswordDto resetPasswordDto, ConstraintValidatorContext constraintValidatorContext) {
        String password = resetPasswordDto.getPassword();
        String confirmPassword = resetPasswordDto.getConfirmPassword();
        
        return password.equals(confirmPassword);
    }
}
