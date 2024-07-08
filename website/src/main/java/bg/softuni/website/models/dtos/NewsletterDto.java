package bg.softuni.website.models.dtos;

import bg.softuni.website.util.validation.UniqueNewsletterEmail;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewsletterDto {
    
    @NotBlank(message = "Oops! Your email cannot be blank")
    @Pattern(regexp = "^\\w{2,}+@\\w{2,}\\.\\w{2,}$", message = "Oops! That does not look like a valid email. Please try again.")
    @UniqueNewsletterEmail(message = "Oops. This email is already in our list.")
    private String newsletterEmail;
    
}
