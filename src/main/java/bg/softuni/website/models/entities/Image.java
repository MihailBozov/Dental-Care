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
    
    @Column(nullable = false)
    private String name;
    
    @Column(columnDefinition = "TEXT")
    private String url;
    
    @ManyToOne()
    @JoinColumn(name = "added_by_user_id", referencedColumnName = "id")
    private User addedByUser;
    
    @ManyToMany(mappedBy = "images", targetEntity = ImageCategory.class)
    private List<ImageCategory> categories;
    
    @OneToMany(mappedBy = "image", targetEntity = User.class)
    private List<User> users;
    
    @OneToMany(mappedBy = "image", targetEntity = Treatment.class)
    private List<Treatment> treatments;
    
    
    public Image() {
        this.categories = new ArrayList<>();
    }
    
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url;
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
    
    public List<User> getUsers() {
        return users;
    }
    
    public void setUsers(List<User> users) {
        this.users = users;
    }
    
    public List<Treatment> getTreatments() {
        return treatments;
    }
    
    public void setTreatments(List<Treatment> treatments) {
        this.treatments = treatments;
    }
}
