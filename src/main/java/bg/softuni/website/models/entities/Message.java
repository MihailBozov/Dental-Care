package bg.softuni.website.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "messages")
public class Message {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(nullable = false, length = 5000)
    private String text;
    
    @ManyToOne
    private UserEntity author;
    
    @ManyToOne
    private UserEntity recipient;
    
    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime;
    
    public Message() {}
    
}
