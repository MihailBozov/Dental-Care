package bg.softuni.website.models.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

public class RegistrationDto {
    
    @Size(min = 3, max = 50, message = "Your first name length should be between 3 and 50 characters")
    private String firstName;
    
    @Size(min = 3, max = 50, message = "Your last name length should be between 3 and 50 characters")
    private String lastName;
    
    @Pattern(regexp = "^\\w+@\\w+\\.\\w+$", message = "Please enter a valid email")
    private String email;
    
    private String gender;
    
    @Size(min = 4, max = 30)
    private String password;
    
    @Size(min = 4, max = 30)
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
    
    public String getGender() {
        return gender;
    }
    
    public void setGender(String gender) {
        this.gender = gender;
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
