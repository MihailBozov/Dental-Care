package bg.softuni.website.models.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
public class Message {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(nullable = false, columnDefinition = "TEXT")
    private String text;
    
    @ManyToOne
    private User author;
    
    @ManyToOne
    private User recipient;
    
    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime;
    
}
