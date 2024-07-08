package bg.softuni.website.util.validation.validators;

import bg.softuni.website.models.entities.Newsletter;
import bg.softuni.website.repositories.NewsletterRepository;
import bg.softuni.website.util.validation.UniqueNewsletterEmail;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class UniqueNewsletterEmailValidator implements ConstraintValidator<UniqueNewsletterEmail, String> {
    
    private NewsletterRepository newsletterRepository;
    
    @Autowired
    public UniqueNewsletterEmailValidator(NewsletterRepository newsletterRepository) {
        this.newsletterRepository = newsletterRepository;
    }
    
    @Override
    public void initialize(UniqueNewsletterEmail constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
    
    @Override
    public boolean isValid(String emailReceived, ConstraintValidatorContext constraintValidatorContext) {
        Optional<Newsletter> emailOpt = this.newsletterRepository.findByNewsletterEmail(emailReceived);
        return emailOpt.isEmpty();
    }
}
