package bg.softuni.website.models.dtos;

import bg.softuni.website.util.validation.UniqueNewsletterEmail;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewsletterDto {
    
    @NotBlank(message = "{email.not.blank}")
    @Pattern(regexp = "^\\w{2,}+@\\w{2,}\\.\\w{2,}$", message = "{email.invalid.pattern}")
    @UniqueNewsletterEmail(message = "{email.already.on.the.list}")
    private String newsletterEmail;
    
}
