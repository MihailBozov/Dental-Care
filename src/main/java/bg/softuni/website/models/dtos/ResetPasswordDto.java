package bg.softuni.website.models.dtos;

import bg.softuni.website.util.validation.MatchRegisterPasswords;
import bg.softuni.website.util.validation.ResetPasswordsMatch;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ResetPasswordsMatch(message = "Oops! Your passwords do not match.")
public class ResetPasswordDto {
    
    private String token;
    @Pattern(regexp = "^\\S+$", message = "Oops! Your password cannot contain whitespaces")
    @Size(min = 4, message = "Oops! Your password is too short. Should be at least 4 characters.")
    @Size(max = 30, message = "Oops! Your password is too long. Should be less than 30 characters.")
    private String password;
    
    private String confirmPassword;
}
