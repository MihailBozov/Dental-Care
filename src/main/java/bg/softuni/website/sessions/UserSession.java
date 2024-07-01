package bg.softuni.website.sessions;

import bg.softuni.website.models.entities.Role;
import bg.softuni.website.models.entities.User;
import bg.softuni.website.models.enums.UserRoles;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;

@Component
@SessionScope
public class UserSession {
    
    private long id;
    
    private String email;
    
    private List<Role> roles;
    
    private String firstName;
    
    private String lastName;
    
   public UserSession() {
       this.id = 0;
       this.email = null;
       this.roles = new ArrayList<>();
       this.firstName = null;
       this.lastName = null;
       
   }
    
    public void login(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.roles = user.getRoles();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
    }
    
    public void logout() {
        this.id = 0;
        this.email = null;
        this.roles = new ArrayList<>();
        this.firstName = null;
        this.lastName = null;
    }
    
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
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
    
    public List<Role> getRoles() {
        return roles;
    }
    
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
