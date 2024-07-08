package bg.softuni.website.models.entities;

import bg.softuni.website.models.enums.DangerLevelName;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "health_conditions")
public class HealthCondition {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(nullable = false, unique = true)
    String name;
    
    @Column(name = "danger_level")
    @Enumerated(EnumType.STRING)
    DangerLevelName dangerLevelName;
    
    @Column(length = 2000)
    private String description;
    
    @ManyToOne
    @JoinColumn(name = "created_by_user_id", referencedColumnName = "id")
    UserEntity createdByUserEntity;
    
    @Column(name = "creation_date")
    private LocalDateTime creationDate;
    
    public HealthCondition() {}
    
    @PrePersist
    protected void onCreate() {
        this.creationDate = LocalDateTime.now();
    }
    
   
}
