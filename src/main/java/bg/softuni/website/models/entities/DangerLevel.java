package bg.softuni.website.models.entities;

import bg.softuni.website.models.enums.DangerLevelName;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
    

}
