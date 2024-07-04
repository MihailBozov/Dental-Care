package bg.softuni.website.models.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

public class UserRegisteredEvent extends ApplicationEvent {
    
    private final String userEmail;
    private final String firstName;
    
    public UserRegisteredEvent(Object source, String userEmail, String firstName) {
        super(source);
        this.userEmail = userEmail;
        this.firstName = firstName;
    }
    
    public String getUserEmail() {
        return userEmail;
    }
    
    public String getFirstName() {
        return firstName;
    }
}
