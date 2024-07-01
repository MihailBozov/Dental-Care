package bg.softuni.website.models.entities;

import bg.softuni.website.models.enums.ImageCategoryName;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "image_categories")
public class ImageCategory {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private ImageCategoryName name;
    
    @ManyToMany
    @JoinTable(name = "image_categories_images", 
    joinColumns = @JoinColumn(name = "image_category_id", referencedColumnName = "id"), 
    inverseJoinColumns = @JoinColumn(name = "image_id", referencedColumnName = "id"))
    private List<Image> images;
    
    public ImageCategory() {}
    
    public ImageCategory(ImageCategoryName name) {
        this.name = name;
        this.images = new ArrayList<>();
    }
    
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public ImageCategoryName getName() {
        return name;
    }
    
    public void setName(ImageCategoryName name) {
        this.name = name;
    }
    
    public List<Image> getImages() {
        return images;
    }
    
    public void setImages(List<Image> images) {
        this.images = images;
    }
    
}
