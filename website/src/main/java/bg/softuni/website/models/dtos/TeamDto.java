package bg.softuni.website.models.dtos;

import bg.softuni.website.models.entities.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TeamDto {
    
    private String firstName;
    private String pictureUrl;
    private List<Role> roles;
    
}
