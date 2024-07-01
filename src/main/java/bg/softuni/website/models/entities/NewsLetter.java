package bg.softuni.website.models.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "newsletter")
public class NewsLetter {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(nullable = false, unique = true)
    private String email;
}
