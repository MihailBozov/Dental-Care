package bg.softuni.website.services;

import bg.softuni.website.models.events.UserActivationUpponRegistrationEvent;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


@Service
public class EmailService {
    private final TemplateEngine templateEngine;
    private final JavaMailSender javaMailSender;
    private final String websiteEmail;
    
    @Autowired
    public EmailService(TemplateEngine templateEngine, 
                        JavaMailSender javaMailSender, 
                        @Value("${EMAIL}") String websiteEmail) {
        this.templateEngine = templateEngine;
        this.javaMailSender = javaMailSender;
        this.websiteEmail = websiteEmail;
    }
    
    public void sendActivationEmail(UserActivationUpponRegistrationEvent userEvent) {
        
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;
        
        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            mimeMessageHelper.setTo(userEvent.getEmail());
            mimeMessageHelper.setFrom(websiteEmail);
            mimeMessageHelper.setReplyTo(websiteEmail);
            mimeMessageHelper.setSubject("Welcome");
            mimeMessageHelper.setText(generateRegistrationEmailBody(userEvent), true);
//            mimeMessageHelper.setText(generateRegistrationEmailBody(firstName, userEmail));
//            mimeMessageHelper.setText("You are at the right place, and totally save!");
            javaMailSender.send(mimeMessageHelper.getMimeMessage());
            
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    
    String generateRegistrationEmailBody(UserActivationUpponRegistrationEvent userEvent) {
        Context context = new Context();
        context.setVariable("userEvent", userEvent);
        context.setVariable("token", userEvent.getToken());
        return this.templateEngine.process("email/registration-confirm-email", context);
    }
}
