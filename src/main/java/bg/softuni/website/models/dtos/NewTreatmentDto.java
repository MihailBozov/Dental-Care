package bg.softuni.website.models.dtos;

import bg.softuni.website.models.entities.Image;
import bg.softuni.website.models.entities.User;
import jakarta.validation.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

public class NewTreatmentDto {
    
    @NotBlank(message = "Oops, the name of the treatment cannot be null")
    private String name;
 
    private String description;
    
    private double price;
    
    User createdByUser;
    
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
