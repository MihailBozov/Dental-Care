package bg.softuni.website.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "activation-token")
@Getter
@Setter
public class ActivationToken {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Basic
    private String token;
    
    @OneToOne(targetEntity = UserEntity.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    UserEntity user;
    
    @Basic
    private LocalDateTime expiryDate;
}
