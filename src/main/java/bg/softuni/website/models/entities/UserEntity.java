package bg.softuni.website.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users")
public class UserEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(name = "first_name")
    private String firstName;
    
    @Column(name = "last_name")
    private String lastName;
    
    @Basic
    private Integer age;
    
    @Basic
    private String gender;
    
    @Column(name = "registration_date")
    LocalDateTime registrationDate;
    
    @Column(nullable = false)
    private String password;
    
    @Column(nullable = false, unique = true)
    private String email;
    
    @Column(name = "phone_number")
    private String phoneNumber;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_health_conditions",
            joinColumns = @JoinColumn(name = "user"), 
            inverseJoinColumns = @JoinColumn(name = "health_conditions"))
    private Set<HealthCondition> healthConditions;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user"),
            inverseJoinColumns = @JoinColumn(name = "roles"))
    private List<Role> roles;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_treatments", 
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "treatments_id"))
    private Set<Treatment> treatments;
    
    @ManyToOne
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    private Image image;
    
    @OneToMany(mappedBy = "author", targetEntity = Message.class)
    private List<Message> sentMessages;
    
    @OneToMany(mappedBy = "recipient", targetEntity = Message.class)
    private List<Message> receivedMessages;
    
    @OneToMany(mappedBy = "createdByUserEntity", targetEntity = Treatment.class)
    List<Treatment> createdTreatments; 
    
    @OneToMany(mappedBy = "addedByUserEntity", targetEntity = Image.class)
    List<Image> addedImages;
    
    @OneToMany(mappedBy = "createdByUserEntity", targetEntity = HealthCondition.class)
    private List<HealthCondition> createdHealthConditions;
    
    @OneToOne
    @JoinColumn(name = "testimonial_id", referencedColumnName = "id")
    private Testimonial testimonial;
    
    public UserEntity() {
        this.roles = new ArrayList<>();
        this.healthConditions = new HashSet<>();
        this.treatments = new HashSet<>();
        this.sentMessages = new ArrayList<>();
        this.receivedMessages = new ArrayList<>();
        this.createdTreatments = new ArrayList<>();
        this.addedImages = new ArrayList<>();
    }
    
    @PrePersist
    public void createRegistrationDateAuto() {
        this.registrationDate = LocalDateTime.now();
    }
    
}
