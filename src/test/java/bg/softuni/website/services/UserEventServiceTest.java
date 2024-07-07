package bg.softuni.website.services;

import bg.softuni.website.models.dtos.RegisterDto;
import bg.softuni.website.models.events.UserActivationUpponRegistrationEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
@ExtendWith(MockitoExtension.class)
class UserEventServiceTest {
    
    @Mock
    private EmailService emailService;
    
    private UserActivationUpponRegistrationEvent userActivationEvent;
    
    @InjectMocks
    private UserEventService userEventService;
    
    @BeforeEach
    void setUp() {
        RegisterDto registerDto = new RegisterDto();
        registerDto.setEmail("test@gmail.com");
        userActivationEvent = new UserActivationUpponRegistrationEvent("TEST", registerDto, "testToken");
    }
    
    @Test
    void userRegistered() {
        userEventService.userRegistered(userActivationEvent);
        verify(emailService, times(1)).sendActivationEmail(userActivationEvent);
    }
}