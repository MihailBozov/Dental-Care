package bg.softuni.website.models.dtos;

import bg.softuni.website.util.validation.UniqueNewsletterEmail;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class NewsletterDto {
    
    @Pattern(regexp = "^\\w{2,}+@\\w{2,}\\.\\w{2,}$", message = "Oops! That does not look like a valid email. Please try again.")
    @UniqueNewsletterEmail(message = "Oops. This email is already in our list.")
    @NotBlank(message = "Oops! Your email cannot be blank")
    private String newsletterEmail;
    
    public String getNewsletterEmail() {
        return newsletterEmail;
    }
    
    public void setNewsletterEmail(String newsletterEmail) {
        this.newsletterEmail = newsletterEmail;
    }
}
