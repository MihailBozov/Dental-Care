package bg.softuni.website.util.validation.validators;

import bg.softuni.website.models.entities.User;
import bg.softuni.website.repositories.UserRepository;
import bg.softuni.website.util.validation.CheckLoginEmailExistence;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class CheckLoginEmailExistenceValidator implements ConstraintValidator<CheckLoginEmailExistence, String> {
    
    private UserRepository userRepository;
    
    @Autowired
    public CheckLoginEmailExistenceValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    
    @Override
    public void initialize(CheckLoginEmailExistence constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
    
    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        Optional<User> user = this.userRepository.findByEmail(email);
        
        return user.isPresent();
    }
}
