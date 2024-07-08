package bg.softuni.website.models.events;

import bg.softuni.website.models.dtos.RegisterDto;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class UserActivationUpponRegistrationEvent extends ApplicationEvent {
    
    private final String email;
    private final String firstName;
    private final String lastName;
    private String token;
    
    public UserActivationUpponRegistrationEvent(Object source, RegisterDto registerDto, String token) {
        super(source);
        this.email = registerDto.getEmail();
        this.firstName = registerDto.getFirstName();
        this.lastName = registerDto.getLastName();
        this.token = token;
    }
}
