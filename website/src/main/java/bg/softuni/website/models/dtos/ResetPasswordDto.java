package bg.softuni.website.models.dtos;

import bg.softuni.website.util.validation.MatchRegisterPasswords;
import bg.softuni.website.util.validation.ResetPasswordsMatch;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ResetPasswordsMatch(message = "{passwords.dont.match}")
public class ResetPasswordDto {
    
    private String token;
    @Pattern(regexp = "^\\S+$", message = "{password.cannot.contain.whitespaces}")
    @Size(min = 4, message = "{password.too.short}")
    @Size(max = 30, message = "{password.too.long}")
    private String password;
    
    private String confirmPassword;
}
