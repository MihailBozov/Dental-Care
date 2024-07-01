package bg.softuni.website.models.entities;

import bg.softuni.website.models.enums.UserRoles;
import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Enumerated(EnumType.STRING)
    private UserRoles name;
    
    @Basic
    private String value;
    
    public Role (){}
    
    public Role(UserRoles name) {
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
    
    public UserRoles getName() {
        return name;
    }
    
    public void setName(UserRoles name) {
        this.name = name;
    }
}
