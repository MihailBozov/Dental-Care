package bg.softuni.website.models.entities;

import bg.softuni.website.models.enums.DangerLevelName;
import jakarta.persistence.*;

@Entity
@Table(name = "danger_levels")
public class DangerLevel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column
    @Enumerated(EnumType.STRING)
    private DangerLevelName name;
    
    public DangerLevel() {
    }
    public DangerLevel(DangerLevelName name) {
        this.name = name;
    }
    
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public DangerLevelName getName() {
        return name;
    }
    
    public void setName(DangerLevelName name) {
        this.name = name;
    }
}
