package bg.softuni.website.models.dtos;

import bg.softuni.website.util.validation.MatchPasswords;
import bg.softuni.website.util.validation.UniqueEmail;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
@MatchPasswords(message = "Oops! Your passwords do not match.")
public class RegisterDto {
    
    @NotBlank(message = "Oops! Your first name cannot be blank")
    @Pattern(regexp = "[A-Za-z ]*", message = "Oops! Your first name can contain only letters")
    @Size(min = 3, message = "Oops! Ensure your username exceeds 3 characters in length.")
    @Size(max = 50, message = "Oops! Please ensure your first name is shorter than 50 characters.")
    private String firstName;
    
    @NotBlank(message = "Oops! Your last name cannot be blank")
    @Pattern(regexp = "[A-Za-z ]*", message = "Oops! Your last name can contain only letters")
    @Size(min = 3, message = "Oops! Ensure your last name exceeds 3 characters in length.")
    @Size(max = 50, message = "Oops! Please make sure your first name is shorter than 50 characters.")
    private String lastName;
    
    @Pattern(regexp = "^\\w+@\\w+\\.\\w+$", message = "Oops! That doesn't look like a valid email. Please try again.")
    @UniqueEmail(message = "Oops. This email is already taken.")
    private String email;
    
    @NotBlank(message = "Oops! Your password cannot be blank")
    @Size(min = 2, message = "Oops! Your password is too short. Please make sure it contains at least 4 characters.")
    @Size(max = 30, message = "Oops! Your password is too long. Please make sure it is 30 characters or less.")
    private String password;
    
    
    private String confirmPassword;
    
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getConfirmPassword() {
        return confirmPassword;
    }
    
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
