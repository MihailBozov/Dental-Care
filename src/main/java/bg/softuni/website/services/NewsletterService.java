package bg.softuni.website.services;

import bg.softuni.website.models.entities.Newsletter;
import bg.softuni.website.repositories.NewsletterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsletterService {
    
    private NewsletterRepository newsletterRepository;
    
    @Autowired
    public NewsletterService(NewsletterRepository newsletterRepository) {
        this.newsletterRepository = newsletterRepository;
    }
    
    public void persistEmail(String newsletterEmail) {
        Newsletter newsletter = new Newsletter(newsletterEmail);
        this.newsletterRepository.save(newsletter);
    }
}
