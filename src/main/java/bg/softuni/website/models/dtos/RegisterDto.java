package bg.softuni.website.models.dtos;

import bg.softuni.website.util.validation.MatchRegisterPasswords;
import bg.softuni.website.util.validation.UniqueRegisterEmail;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MatchRegisterPasswords(message = "Oops! Your passwords do not match.")
public class RegisterDto {
    
    @NotBlank(message = "Oops! Your first name cannot be blank")
    @Pattern(regexp = "[A-Za-z ]*", message = "Oops! Your first name can contain only letters")
    @Size(min = 2, message = "Oops! Your first name is too short. Should be at least 2 characters.")
    @Size(max = 19, message = "Oops! Your first name is too long. Should be less than 20 characters.")
    private String firstName;
    
    @NotBlank(message = "Oops! Your last name cannot be blank")
    @Pattern(regexp = "[A-Za-z ]*", message = "Oops! Your last name can contain only letters")
    @Size(min = 2, message = "Oops! Your last name is too short. Should be at least 2 characters.")
    @Size(max = 20, message = "Oops! Your last name is too long. Should be less than 20 characters.")
    private String lastName;
    
    @Pattern(regexp = "^\\w{2,}+@\\w{2,}\\.\\w{2,}$", message = "Oops! That doesn't look like a valid email. Please try again.")
    @UniqueRegisterEmail(message = "Oops. This email is already taken.")
    private String email;
    
    @Pattern(regexp = "^\\S+$", message = "Oops! Your password cannot contain whitespaces")
    @Size(min = 4, message = "Oops! Your password is too short. Should be at least 4 characters.")
    @Size(max = 30, message = "Oops! Your password is too long. Should be less than 30 characters.")
    private String password;
    
    
    private String confirmPassword;
    
}
