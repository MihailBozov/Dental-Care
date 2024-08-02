package bg.softuni.website.models.dtos;

import bg.softuni.website.models.entities.UserEntity;
import bg.softuni.website.util.validation.FileNotEmpty;
import bg.softuni.website.util.validation.UniqueTreatmentImageName;
import bg.softuni.website.util.validation.UniqueTreatmentName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class NewTreatmentDto {
    
    @NotBlank(message = "{treatment.name.cannot.be.blank}")
    @UniqueTreatmentName(message = "{treatment.already.exists}")
    private String name;
 
    private String description;
    
    @Positive(message = "{treatment.price.must.be.positive.number}")
    private double price;
    
    UserEntity createdByUserEntity;
    
    @UniqueTreatmentImageName(message = "{treatment.image.name.must.be.unique}")
    @FileNotEmpty(message = "{treatment.image.should.not.be.empty}")
    private MultipartFile image;
    
}
