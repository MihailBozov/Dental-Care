package bg.softuni.website.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "images")
public class Image {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(nullable = false, unique = true)
    private String name;
    
    @Column(columnDefinition = "TEXT")
    private String url;
    
    @ManyToOne()
    @JoinColumn(name = "added_by_user_id", referencedColumnName = "id")
    private UserEntity addedByUserEntity;
    
    @ManyToMany(mappedBy = "images", targetEntity = ImageCategory.class)
    private List<ImageCategory> categories;
    
    @OneToMany(mappedBy = "image", targetEntity = UserEntity.class)
    private List<UserEntity> userEntities;
    
    @OneToMany(mappedBy = "image", targetEntity = Treatment.class)
    private List<Treatment> treatments;
    
    
    public Image() {
        this.categories = new ArrayList<>();
    }
    
}
