package bg.softuni.website.models.dtos;

import bg.softuni.website.models.entities.Role;

import java.util.List;

public class TeamDto {
    
    private String firstName;
    private String pictureUrl;
    private List<Role> roles;
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getPictureUrl() {
        return pictureUrl;
    }
    
    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }
    
    public List<Role> getRoles() {
        return roles;
    }
    
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
