package bg.softuni.website.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {
    
    
    @Bean
    public JavaMailSender javaMailSender(@Value("${spring.mail.host}") String host,
                                         @Value("${spring.mail.port}") int port,
                                         @Value("${spring.mail.username}") String username,
                                         @Value("${spring.mail.password}") String password) {
        
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(host);
        javaMailSender.setPort(port);
        javaMailSender.setUsername(username);
        javaMailSender.setPassword(password);
        javaMailSender.setDefaultEncoding("UTF-8");
        javaMailSender.setJavaMailProperties(mailProperties(port));
        
        return javaMailSender;
    }
    
    private Properties mailProperties(int port) {
        
        Properties mailProperties = new Properties();
        mailProperties.setProperty("mail.smtp.auth", "true");
        mailProperties.setProperty("mail.transport.protocol", "smtp");
        if (port == 465) {
            mailProperties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            mailProperties.setProperty("mail.smtp.socketFactory.fallback", "false");
            mailProperties.setProperty("mail.smtp.socketFactory.port", String.valueOf(port));
        } else if (port == 587) {
            mailProperties.setProperty("mail.smtp.starttls.enable", "true");
        }
        return mailProperties;
      
    }
}
