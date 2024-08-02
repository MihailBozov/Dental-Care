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
@EditUniqueTreatmentImageName(message = "{treatment.image.name.must.be.unique}")
@EditUniqueTreatmentName(message = "{treatment.already.exists}")
public class EditTreatmentDto {
    
    private long id;
    
    @NotBlank(message = "{treatment.name.cannot.be.blank}")
    private String name;
    
    private String description;
    
    @Positive(message = "{treatment.price.must.be.positive.number}")
    private double price;
    
    UserEntity createdByUserEntity;
    
    private MultipartFile image;
}
