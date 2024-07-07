package bg.softuni.website.services;

import bg.softuni.website.models.dtos.RegisterDto;
import bg.softuni.website.models.entities.UserEntity;
import bg.softuni.website.models.events.UserActivationUpponRegistrationEvent;
import bg.softuni.website.repositories.UserRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmailServiceTest {
    
    @Mock
    private TemplateEngine templateEngine;
    
    @Mock
    private JavaMailSender javaMailSender;
    
    @Mock
    private UserRepository userRepository;
    
    @InjectMocks
    private EmailService emailService;
    
//    @Value("${spring.mail.username}")
    private String websiteEmail;
    
    @BeforeEach
    void setUp() {
        emailService = new EmailService(templateEngine, javaMailSender, "mihailbozov001@gmail.com", userRepository);
    }
    
    @Test
    void sendActivationEmail(){
        RegisterDto registerDto = new RegisterDto();
        registerDto.setEmail("user@gmail.com");
        UserActivationUpponRegistrationEvent userEvent = new UserActivationUpponRegistrationEvent("TEST", registerDto, "testToken");
        
        MimeMessage mimeMessage = mock(MimeMessage.class);

        when(javaMailSender.createMimeMessage())
                .thenReturn(mimeMessage);
        
        when(templateEngine.process(eq("email/registration-confirm-email"), any(Context.class)))
                .thenReturn("Test Email Body");
        
        emailService.sendActivationEmail(userEvent);
        
        verify(javaMailSender, times(1)).send(mimeMessage);
        
        ArgumentCaptor<Context> contextCaptor = ArgumentCaptor.forClass(Context.class);
        verify(templateEngine).process(eq("email/registration-confirm-email"), contextCaptor.capture());
        
        Context capturedContext = contextCaptor.getValue();
        Assertions.assertEquals(userEvent, capturedContext.getVariable("userEvent"));
        Assertions.assertEquals("testToken", capturedContext.getVariable("token"));
    }
    
    @Test
    void sendActivationEmailShouldThrowRuntimeException() {
        RegisterDto registerDto = new RegisterDto();
        registerDto.setEmail("user@gmail.com");
        UserActivationUpponRegistrationEvent userEvent = new UserActivationUpponRegistrationEvent("TEST", registerDto, "testToken");
        
        when(javaMailSender.createMimeMessage())
                .thenThrow(new RuntimeException("Email sending failed"));
        
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            emailService.sendActivationEmail(userEvent);
        });
        Assertions.assertEquals("Email sending failed", exception.getMessage());
    }
    
    @Test
    void sendActivationEmailShouldThrowMessagingException(){
        RegisterDto registerDto = new RegisterDto();
        registerDto.setEmail("user@gmail.com");
        UserActivationUpponRegistrationEvent userEvent = new UserActivationUpponRegistrationEvent("TEST", registerDto, "testToken");
        
        MimeMessage mimeMessage = mock(MimeMessage.class);
        when(javaMailSender.createMimeMessage()).thenReturn(mimeMessage);
        
        when(templateEngine.process(eq("email/registration-confirm-email"), any(Context.class)))
                .thenReturn("Test Email Body");
        
        doAnswer(invocation -> {
            throw new MessagingException("Simulated MessagingException");
        }).when(javaMailSender).send(mimeMessage);
        
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            emailService.sendActivationEmail(userEvent);
        });
        
        Throwable cause = exception.getCause();
        Assertions.assertEquals(MessagingException.class, cause.getClass());
        Assertions.assertEquals("Simulated MessagingException", cause.getMessage());
        
    }
    
    @Test
    void sendResetPasswordEmail() {
        UserEntity user = new UserEntity();
        user.setEmail("user@gmail.com");
        String token = "testToken";
        MimeMessage mimeMessage = mock(MimeMessage.class);
        
        when(javaMailSender.createMimeMessage())
                .thenReturn(mimeMessage);
        
        when(templateEngine.process(eq("email/reset-password-email"), any(Context.class)))
                .thenReturn("This is example email body");
        
        emailService.sendResetPasswordEmail(user, token);
        
        ArgumentCaptor<Context> contextCaptor = ArgumentCaptor.forClass(Context.class);
        verify(templateEngine, times(1))
                .process(eq("email/reset-password-email"), contextCaptor.capture());
        
        Assertions.assertEquals(user, contextCaptor.getValue().getVariable("userReset"));
        Assertions.assertEquals(token, contextCaptor.getValue().getVariable("token"));
        
        verify(javaMailSender, times(1))
                .send(mimeMessage);
    }
    
    @Test
    void sendResetPasswordEmailShouldThrowMessagingExceptionAndRuntimeException() {
        UserEntity user = new UserEntity();
        user.setEmail("user@gmail.com");
        String token = "testToken";
        
        MimeMessage mimeMessage = mock(MimeMessage.class);
        
        when(javaMailSender.createMimeMessage())
                .thenReturn(mimeMessage);
        
        when(templateEngine.process(eq("email/reset-password-email"), any(Context.class)))
                .thenReturn("This is example email body");
        
        doAnswer(invocation -> {
            throw new MessagingException("Simulated MessagingException");
        }).when(javaMailSender).send(mimeMessage);
        
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            emailService.sendResetPasswordEmail(user, token);
        });
        
        Throwable cause = exception.getCause();
        
        Assertions.assertEquals(MessagingException.class, cause.getClass());
        Assertions.assertEquals("Simulated MessagingException", cause.getMessage());
    }
}