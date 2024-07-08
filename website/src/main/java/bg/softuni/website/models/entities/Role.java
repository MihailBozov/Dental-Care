package bg.softuni.website.models.entities;

import bg.softuni.website.models.enums.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "roles")
public class Role {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Enumerated(EnumType.STRING)
    private UserRole name;
    
    @Basic
    private String value;
    
    public Role (){}
    
    public Role(UserRole name) {
        this.name = name;
    }
    
}
