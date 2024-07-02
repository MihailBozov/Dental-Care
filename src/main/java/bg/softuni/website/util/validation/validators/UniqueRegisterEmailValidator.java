package bg.softuni.website.util.validation.validators;

import bg.softuni.website.models.entities.User;
import bg.softuni.website.repositories.UserRepository;
import bg.softuni.website.util.validation.UniqueRegisterEmail;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class UniqueRegisterEmailValidator implements ConstraintValidator<UniqueRegisterEmail, String> {
    
    UserRepository userRepository;
    
    @Autowired
    public UniqueRegisterEmailValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @Override
    public void initialize(UniqueRegisterEmail constraintAnnotation) {
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
