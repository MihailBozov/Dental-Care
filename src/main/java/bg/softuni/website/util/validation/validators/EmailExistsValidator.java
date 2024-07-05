package bg.softuni.website.util.validation.validators;

import bg.softuni.website.models.entities.UserEntity;
import bg.softuni.website.repositories.UserRepository;
import bg.softuni.website.util.validation.EmailExists;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class EmailExistsValidator implements ConstraintValidator<EmailExists, String> {
    private UserRepository userRepository;
    
    @Autowired
    public EmailExistsValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        Optional<UserEntity> user = this.userRepository.findByEmail(email);
        return user.isPresent() && user.get().isActive();
    }
}
