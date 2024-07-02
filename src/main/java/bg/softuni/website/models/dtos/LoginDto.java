package bg.softuni.website.models.dtos;

import bg.softuni.website.util.validation.CheckLoginEmailExistence;
import bg.softuni.website.util.validation.CheckLoginPassword;
import jakarta.validation.constraints.Pattern;

@CheckLoginPassword
public class LoginDto {

    @Pattern(regexp = "^\\w{2,}+@\\w{2,}\\.\\w{2,}$", message = "Oops! That does not look like a valid email. Please try again.")
    @CheckLoginEmailExistence
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
