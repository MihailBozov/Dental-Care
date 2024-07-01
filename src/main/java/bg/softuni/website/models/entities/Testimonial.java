package bg.softuni.website.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "testimonials")
public class Testimonial {
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Basic
    private String content;
    
    @OneToOne(mappedBy = "testimonial", targetEntity = User.class)
    private User user;
    
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
}
