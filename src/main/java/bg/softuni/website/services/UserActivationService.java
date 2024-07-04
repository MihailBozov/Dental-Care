package bg.softuni.website.services;

import bg.softuni.website.models.events.UserRegisteredEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.mail.MailSender;
import org.springframework.stereotype.Service;

@Service
public class UserActivationService {
    
    private EmailService emailService;
    
    @Autowired
    public UserActivationService(EmailService emailService) {
        this.emailService = emailService;
    }
    
    @EventListener(UserRegisteredEvent.class)
    void userRegistered(UserRegisteredEvent event) {
        emailService.sendRegistrationEmail(event.getUserEmail(), event.getFirstName());
      
    }
}
