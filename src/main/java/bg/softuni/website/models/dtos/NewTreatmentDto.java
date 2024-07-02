package bg.softuni.website.models.dtos;

import bg.softuni.website.models.entities.UserEntity;
import bg.softuni.website.util.validation.FileNotEmpty;
import bg.softuni.website.util.validation.UniqueTreatmentName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class NewTreatmentDto {
    
    @NotBlank(message = "Oops, the name of the treatment cannot be null")
    @UniqueTreatmentName(message = "Oops, that treatment already exists")
    private String name;
 
    private String description;
    
    @Positive(message = "Oops, the price must be a positive number")
    private double price;
    
    UserEntity createdByUserEntity;
    
    @FileNotEmpty(message = "Oops, it looks like there is a problem with the image")
    private MultipartFile image;
    
}
