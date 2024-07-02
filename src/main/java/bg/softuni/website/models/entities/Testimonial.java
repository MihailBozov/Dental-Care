package bg.softuni.website.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "testimonials")
public class Testimonial {
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Basic
    private String content;
    
    @Column(name="count_stars")
    private int countStars;
    
    @OneToOne(mappedBy = "testimonial", targetEntity = UserEntity.class)
    private UserEntity userEntity;
    
}
