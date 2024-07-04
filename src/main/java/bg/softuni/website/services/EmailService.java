package bg.softuni.website.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
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
    
    
    public EmailService(TemplateEngine templateEngine, JavaMailSender javaMailSender, @Value("${EMAIL}") String websiteEmail) {
        this.templateEngine = templateEngine;
        this.javaMailSender = javaMailSender;
        this.websiteEmail = websiteEmail;
    }
    
    public void sendRegistrationEmail(String userEmail, String firstName) {
        
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;
        
        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            mimeMessageHelper.setTo(userEmail);
            mimeMessageHelper.setFrom(websiteEmail);
            mimeMessageHelper.setReplyTo(websiteEmail);
            mimeMessageHelper.setSubject("Welcome");
//            mimeMessageHelper.setText(generateRegistrationEmailBody(firstName, userEmail));
            mimeMessageHelper.setText("You are at the right place, and totally save!");
            javaMailSender.send(mimeMessageHelper.getMimeMessage());
            
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    
    String generateRegistrationEmailBody(String firstName, String email) {
        Context context = new Context();
        context.setVariable("firstName", firstName);
        context.setVariable("email", email);
        
        return this.templateEngine.process("email/registration-confirm-email", context);
    }
}
