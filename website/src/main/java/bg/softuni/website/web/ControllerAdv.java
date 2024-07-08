package bg.softuni.website.web;

import bg.softuni.website.models.dtos.NewsletterDto;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class ControllerAdv {
    
    @ModelAttribute("newsletterDto")
    public NewsletterDto newsletterDto() {
        return new NewsletterDto();
    }
    
    
}
