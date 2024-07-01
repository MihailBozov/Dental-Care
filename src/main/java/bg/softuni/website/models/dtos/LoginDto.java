package bg.softuni.website.models.dtos;

import bg.softuni.website.util.validation.CheckEmailExistence;
import bg.softuni.website.util.validation.CheckPassword;
import jakarta.validation.constraints.Pattern;

@CheckPassword
public class LoginDto {

    @Pattern(regexp = "^\\w+@\\w+\\.\\w+$", message = "Oops! That does not look like a valid email. Please try again.")
    @CheckEmailExistence
    String email;
   
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
