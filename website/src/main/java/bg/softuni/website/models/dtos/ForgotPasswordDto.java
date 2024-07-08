package bg.softuni.website.models.dtos;

import bg.softuni.website.util.validation.EmailExists;
import bg.softuni.website.util.validation.ResetPasswordEmailAlreadySent;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ForgotPasswordDto {
    
    @Pattern(regexp = "^\\w{2,}+@\\w{2,}\\.\\w{2,}$", message = "Oops! That does not look like a valid email. Please try again.")
    @EmailExists(message = "Oops! It looks like there is no registered user with the email address you provided.")
    @ResetPasswordEmailAlreadySent(message = "Oops! A reset password email has already been sent to your email address.")
    String email;
}
