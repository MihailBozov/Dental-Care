package bg.softuni.website.models.entities;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "images")
public class Image {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(name = "image_data")
    private byte[] imageData;
    
    @Column(name = "unique_image_name", unique = true)
    private String uniqueImageName;
    
    @ManyToOne()
    @JoinColumn(name = "added_by_user_id", referencedColumnName = "id")
    private User addedByUser;
    
    @ManyToMany(mappedBy = "images", targetEntity = ImageCategory.class)
    private List<ImageCategory> categories;
    
    public Image() {
        this.categories = new ArrayList<>();
    }
    
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public byte[] getImageData() {
        return imageData;
    }
    
    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }
    
    public User getAddedByUser() {
        return addedByUser;
    }
    
    public void setAddedByUser(User addedByUser) {
        this.addedByUser = addedByUser;
    }
    
    public List<ImageCategory> getCategories() {
        return categories;
    }
    
    public void setCategories(List<ImageCategory> categories) {
        this.categories = categories;
    }
}
