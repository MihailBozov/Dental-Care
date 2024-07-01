package bg.softuni.website.models.dtos;

import jakarta.validation.constraints.NotBlank;

public class LoginDto {
    
    @NotBlank(message = "Please enter a valid email")
    String email;
    
    @NotBlank(message = "Please enter a valid password")
    String password;
    
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
}
