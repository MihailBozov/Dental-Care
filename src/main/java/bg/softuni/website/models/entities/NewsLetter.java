package bg.softuni.website.models.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

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
    
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public String getNewsletterEmail() {
        return newsletterEmail;
    }
    
    public void setNewsletterEmail(String newsletterEmail) {
        this.newsletterEmail = newsletterEmail;
    }
    
    public LocalDateTime getCreationDate() {
        return creationDate;
    }
    
    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
}
