package bg.softuni.website.models.entities;

import bg.softuni.website.models.enums.DangerLevel;
import jakarta.persistence.*;

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
    DangerLevel dangerLevel;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
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
    
    public DangerLevel getDangerLevel() {
        return dangerLevel;
    }
    
    public void setDangerLevel(DangerLevel dangerLevel) {
        this.dangerLevel = dangerLevel;
    }
}
