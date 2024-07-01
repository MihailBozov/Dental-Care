package bg.softuni.website.util.validation.validators;

import bg.softuni.website.models.entities.User;
import bg.softuni.website.repositories.UserRepository;
import bg.softuni.website.util.validation.CheckEmailExistence;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.annotation.Annotation;
import java.util.Optional;

public class CheckEmailExistenceValidator implements ConstraintValidator<CheckEmailExistence, String> {
    
    private UserRepository userRepository;
    
    @Autowired
    public CheckEmailExistenceValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    
    @Override
    public void initialize(CheckEmailExistence constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
    
    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        Optional<User> user = this.userRepository.findByEmail(email);
        
        return user.isPresent();
    }
}
