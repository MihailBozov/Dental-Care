package bg.softuni.website.models.entities;

import bg.softuni.website.models.enums.UserRole;
import jakarta.persistence.*;

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
    
    public String getValue() {
        return value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }
    
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public UserRole getName() {
        return name;
    }
    
    public void setName(UserRole name) {
        this.name = name;
    }
}
