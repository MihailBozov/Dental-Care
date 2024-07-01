package bg.softuni.website.models.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(name = "first_name")
    private String firstName;
    
    @Column(name = "last_name")
    private String lastName;
    
    @Basic
    private int age;
    
    @Basic
    private String gender;
    
    @Basic
    LocalDateTime registrationDate;
    
    @Column(nullable = false, unique = true)
    private String username;
    
    @Column(nullable = false)
    private String password;
    
    @Column(nullable = false, unique = true)
    private String email;
    
    @Basic
    private String pictureUrl;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_health_conditions",
            joinColumns = @JoinColumn(name = "user"), 
            inverseJoinColumns = @JoinColumn(name = "health_conditions"))
    private Set<HealthCondition> healthConditions;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user"),
            inverseJoinColumns = @JoinColumn(name = "roles"))
    private Set<Role> roles;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_treatments", 
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "treatments_id"))
    private Set<Treatment> treatments;
    
    @OneToMany(mappedBy = "author", targetEntity = Message.class)
    private List<Message> sentMessages;
    
    @OneToMany(mappedBy = "recipient", targetEntity = Message.class)
    private List<Message> receivedMessages;
    
    public User() {
        this.roles = new HashSet<>();
        this.healthConditions = new HashSet<>();
        this.treatments = new HashSet<>();
        this.sentMessages = new ArrayList<>();
        this.receivedMessages = new ArrayList<>();
    }
    
    @PrePersist
    public void createRegistrationDateAuto() {
        this.registrationDate = LocalDateTime.now();
    }
    
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public int getAge() {
        return age;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPictureUrl() {
        return pictureUrl;
    }
    
    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }
    
    public Set<HealthCondition> getHealthConditions() {
        return healthConditions;
    }
    
    public void setHealthConditions(Set<HealthCondition> healthConditions) {
        this.healthConditions = healthConditions;
    }
    
    public Set<Role> getRoles() {
        return roles;
    }
    
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
    
    public Set<Treatment> getTreatments() {
        return treatments;
    }
    
    public void setTreatments(Set<Treatment> treatments) {
        this.treatments = treatments;
    }
    
    public List<Message> getSentMessages() {
        return sentMessages;
    }
    
    public void setSentMessages(List<Message> sentMessages) {
        this.sentMessages = sentMessages;
    }
    
    public List<Message> getReceivedMessages() {
        return receivedMessages;
    }
    
    public void setReceivedMessages(List<Message> receivedMessages) {
        this.receivedMessages = receivedMessages;
    }
    
    public String getGender() {
        return gender;
    }
    
    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }
    
    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }
}
