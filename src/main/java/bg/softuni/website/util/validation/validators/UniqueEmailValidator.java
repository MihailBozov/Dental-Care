package bg.softuni.website.util.validation.validators;

import bg.softuni.website.models.entities.User;
import bg.softuni.website.repositories.UserRepository;
import bg.softuni.website.util.validation.UniqueEmail;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Optional;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {
    
    UserRepository userRepository;
    
    public UniqueEmailValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @Override
    public void initialize(UniqueEmail constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
    
    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        
        Optional<User> findUser = this.userRepository.findByEmail(email);
        
        if (findUser.isPresent()) {
            return false;
        }
        
        return true;
    }
}
