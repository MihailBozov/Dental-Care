package bg.softuni.website.models.dtos;

import bg.softuni.website.util.validation.CheckEmailExistence;
import jakarta.validation.constraints.Pattern;

public class NewsletterDto {
    
    @Pattern(regexp = "^\\w+@\\w+\\.\\w+$", message = "Oops! That does not look like a valid email. Please try again.")
    @CheckEmailExistence(message = "Oops. This email is already in our list.")
    private String newsletterEmail;
    
    public String getNewsletterEmail() {
        return newsletterEmail;
    }
    
    public void setNewsletterEmail(String newsletterEmail) {
        this.newsletterEmail = newsletterEmail;
    }
}
