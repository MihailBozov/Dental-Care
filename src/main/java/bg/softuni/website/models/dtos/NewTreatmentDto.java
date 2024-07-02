package bg.softuni.website.models.dtos;

import bg.softuni.website.models.entities.Image;
import bg.softuni.website.models.entities.User;
import bg.softuni.website.util.validation.FileNotEmpty;
import bg.softuni.website.util.validation.UniqueTreatmentName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.springframework.web.multipart.MultipartFile;

public class NewTreatmentDto {
    
    @NotBlank(message = "Oops, the name of the treatment cannot be null")
    @UniqueTreatmentName(message = "Oops, that treatment already exists")
    private String name;
 
    private String description;
    
    @Positive(message = "Oops, the price must be a positive number")
    private double price;
    
    User createdByUser;
    
    @FileNotEmpty(message = "Oops, it looks like there is a problem with the image")
    private MultipartFile image;
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    public User getCreatedByUser() {
        return createdByUser;
    }
    
    public void setCreatedByUser(User createdByUser) {
        this.createdByUser = createdByUser;
    }
    
    public MultipartFile getImage() {
        return image;
    }
    
    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
