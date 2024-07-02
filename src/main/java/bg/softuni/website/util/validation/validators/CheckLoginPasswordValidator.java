package bg.softuni.website.util.validation.validators;

import bg.softuni.website.models.dtos.LoginDto;
import bg.softuni.website.models.entities.User;
import bg.softuni.website.repositories.UserRepository;
import bg.softuni.website.util.validation.CheckLoginPassword;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

public class CheckLoginPasswordValidator implements ConstraintValidator<CheckLoginPassword, LoginDto> {
    
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    public CheckLoginPasswordValidator(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    @Override
    public void initialize(CheckLoginPassword constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
    
    @Override
    public boolean isValid(LoginDto loginDto, ConstraintValidatorContext constraintValidatorContext) {
        String password = loginDto.getPassword();
        Optional<User> user = userRepository.findByEmail(loginDto.getEmail());
        
        if (user.isPresent()) {
            return this.passwordEncoder.matches(password, user.get().getPassword());
        }
        return false;
    }
}
