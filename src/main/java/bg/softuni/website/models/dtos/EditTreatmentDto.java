package bg.softuni.website.models.dtos;

import bg.softuni.website.models.entities.UserEntity;
import bg.softuni.website.util.validation.EditUniqueTreatmentImageName;
import bg.softuni.website.util.validation.EditUniqueTreatmentName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;


@Getter
@Setter
@EditUniqueTreatmentImageName(message = "Oops, it looks like there is a file with that name already")
@EditUniqueTreatmentName(message = "Oops, that treatment already exists")
public class EditTreatmentDto {
    
    private long id;
    
    @NotBlank(message = "Oops, the name of the treatment cannot be null")
    private String name;
    
    private String description;
    
    @Positive(message = "Oops, the price must be a positive number")
    private double price;
    
    UserEntity createdByUserEntity;
    
    private MultipartFile image;
}
