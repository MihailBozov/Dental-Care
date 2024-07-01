package bg.softuni.website.models.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "newsletter")
public class NewsLetter {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(nullable = false, unique = true)
    private String email;
    
    @Column(name = "creation_date")
    private LocalDateTime creationDate;
    
    public NewsLetter() {}
    
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
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
}
