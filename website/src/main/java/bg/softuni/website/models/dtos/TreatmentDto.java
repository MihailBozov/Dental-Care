package bg.softuni.website.models.dtos;

import bg.softuni.website.models.entities.Image;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TreatmentDto {
    
    private long id;
    
    private String name;
    
    private String description;
    
    private Image image;
    
}
