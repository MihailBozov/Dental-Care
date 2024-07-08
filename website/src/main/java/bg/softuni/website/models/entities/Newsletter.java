package bg.softuni.website.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "newsletter")
public class Newsletter {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(name = "newsletter_email", nullable= false, unique = true)
    private String newsletterEmail;
    
    @Column(name = "creation_date")
    private LocalDateTime creationDate;
    
    public Newsletter() {
    }
    
    public Newsletter(String newsletterEmail) {
        this.newsletterEmail = newsletterEmail;
    }
    
    @PrePersist
    protected void onCreate() {
        this.creationDate = LocalDateTime.now();
    }
    
}
