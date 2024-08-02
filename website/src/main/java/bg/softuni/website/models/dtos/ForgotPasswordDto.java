package bg.softuni.website.models.dtos;

import bg.softuni.website.util.validation.EmailExists;
import bg.softuni.website.util.validation.ResetPasswordEmailAlreadySent;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ForgotPasswordDto {
    
    @Pattern(regexp = "^\\w{2,}+@\\w{2,}\\.\\w{2,}$", message = "{email.invalid.pattern}")
    @EmailExists(message = "{email.does.not.exist}")
    @ResetPasswordEmailAlreadySent(message = "{password.reset.already.sent}")
    String email;
}
