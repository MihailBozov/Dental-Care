package bg.softuni.website.services;

import bg.softuni.website.models.entities.UserEntity;
import bg.softuni.website.models.events.UserActivationUpponRegistrationEvent;
import bg.softuni.website.repositories.UserRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Optional;


@Service
public class EmailService {
    private final TemplateEngine templateEngine;
    private final JavaMailSender javaMailSender;
    private final String websiteEmail;
    private final UserRepository userRepository;
    
    @Autowired
    public EmailService(TemplateEngine templateEngine,
                        JavaMailSender javaMailSender,
                        @Value("${spring.mail.username}") String websiteEmail, UserRepository userRepository) {
        this.templateEngine = templateEngine;
        this.javaMailSender = javaMailSender;
        this.websiteEmail = websiteEmail;
        this.userRepository = userRepository;
    }
    
    public void sendActivationEmail(UserActivationUpponRegistrationEvent userEvent) {
        
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;
        
        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, "UTF-8");
            mimeMessageHelper.setTo(userEvent.getEmail());
            mimeMessageHelper.setFrom(websiteEmail);
            mimeMessageHelper.setReplyTo(websiteEmail);
            mimeMessageHelper.setSubject("Confirm Email");
            mimeMessageHelper.setText(generateActivationEmailBody(userEvent), true);
//            mimeMessageHelper.setText(generateRegistrationEmailBody(firstName, userEmail));
//            mimeMessageHelper.setText("You are at the right place, and totally save!");
            javaMailSender.send(mimeMessageHelper.getMimeMessage());
            
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    
    String generateActivationEmailBody(UserActivationUpponRegistrationEvent userEvent) {
        Context context = new Context();
        context.setVariable("userEvent", userEvent);
        context.setVariable("token", userEvent.getToken());
        return this.templateEngine.process("email/registration-confirm-email", context);
    }
    
    
    public void sendResetPasswordEmail(UserEntity user, String token) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        
        
        try {
            mimeMessageHelper.setTo(user.getEmail());
            mimeMessageHelper.setFrom(websiteEmail);
            mimeMessageHelper.setReplyTo(websiteEmail);
            mimeMessageHelper.setSubject("Reset Password");
            mimeMessageHelper.setText(generateResetPasswordEmailBody(user, token), true);
            javaMailSender.send(mimeMessageHelper.getMimeMessage());
            
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    
    public String generateResetPasswordEmailBody(UserEntity user, String token) {
        Context context = new Context();
        context.setVariable("userReset", user);
        context.setVariable("token", token);
        return this.templateEngine.process("email/reset-password-email", context);
    }
}












