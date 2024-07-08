package bg.softuni.website.services;

import bg.softuni.website.models.entities.Newsletter;
import bg.softuni.website.repositories.NewsletterRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NewsletterServiceTest {
    
    @Mock
    private NewsletterRepository newsletterRepository;
    
    @InjectMocks
    private NewsletterService newsletterService;
    
    
    @Test
    void persistEmail() {
        String email = "test@gmail.com";
        Newsletter newsletter = new Newsletter(email);
        
        newsletterService.persistEmail(email);
        
        ArgumentCaptor<Newsletter> captor = ArgumentCaptor.forClass(Newsletter.class);
        
        verify(newsletterRepository, times(1))
                .saveAndFlush(captor.capture());
        
        Newsletter newsletterCaptured = captor.getValue();
        Assertions.assertEquals(email, newsletterCaptured.getNewsletterEmail());
    }
}