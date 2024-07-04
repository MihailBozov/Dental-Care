package bg.softuni.website.services;

import bg.softuni.website.models.events.UserActivationUpponRegistrationEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class UserEventService {
    
    private EmailService emailService;
    
    @Autowired
    public UserEventService(EmailService emailService) {
        this.emailService = emailService;
    }
    
    @EventListener(UserActivationUpponRegistrationEvent.class)
    void userRegistered(UserActivationUpponRegistrationEvent event) {
        emailService.sendActivationEmail(event);
      
    }
}
