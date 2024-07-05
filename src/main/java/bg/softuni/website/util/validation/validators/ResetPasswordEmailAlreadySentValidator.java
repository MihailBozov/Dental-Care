package bg.softuni.website.util.validation.validators;

import bg.softuni.website.models.entities.ActivationToken;
import bg.softuni.website.models.entities.UserEntity;
import bg.softuni.website.repositories.ActivationTokenRepository;
import bg.softuni.website.repositories.UserRepository;
import bg.softuni.website.util.validation.ResetPasswordEmailAlreadySent;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class ResetPasswordEmailAlreadySentValidator implements ConstraintValidator<ResetPasswordEmailAlreadySent, String> {
    
   private ActivationTokenRepository activationTokenRepository;
   private UserRepository userRepository;
    
   @Autowired
    public ResetPasswordEmailAlreadySentValidator(ActivationTokenRepository activationTokenRepository, UserRepository userRepository) {
       this.activationTokenRepository = activationTokenRepository;
       this.userRepository = userRepository;
   }
    
    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        Optional<ActivationToken> activationToken = this.activationTokenRepository.findByUserEmail(email);
        Optional<UserEntity> user = this.userRepository.findByEmail(email);
        return activationToken.isEmpty() && user.get().isActive();
    }
}
