package bg.softuni.website.models.entities;

import bg.softuni.website.models.enums.DangerLevelName;
import jakarta.persistence.*;

import java.time.LocalDateTime;

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
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    @ManyToOne
    @JoinColumn(name = "created_by_user_id", referencedColumnName = "id")
    User createdByUser;
    
    @Column(name = "creation_date")
    private LocalDateTime creationDate;
    
    public HealthCondition() {}
    
    @PrePersist
    protected void onCreate() {
        this.creationDate = LocalDateTime.now();
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
    
    public DangerLevelName getDangerLevel() {
        return dangerLevelName;
    }
    
    public void setDangerLevel(DangerLevelName dangerLevelName) {
        this.dangerLevelName = dangerLevelName;
    }
}
