package bg.softuni.website.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "treatments")
public class Treatment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(nullable = false, unique = true)
    private String name;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    @Basic
    private double price;
    
    @ManyToMany(mappedBy = "treatments", targetEntity = UserEntity.class)
    private Set<UserEntity> userEntities;
    
    @ManyToOne
    @JoinColumn(name = "created_by_user_id", referencedColumnName = "id")
    UserEntity createdByUserEntity;
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    private Image image;
    
    @Column(name = "creation_date")
    LocalDateTime creationDate;
    
    public Treatment() {
        this.userEntities = new HashSet<>();
    }
    
    @PrePersist
    protected void onCreate() {
        this.creationDate = LocalDateTime.now();
    }
    
}
