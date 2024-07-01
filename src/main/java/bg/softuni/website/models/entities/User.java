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
    
    @Column(name = "picture_url")
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
    private List<Role> roles;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_treatments", 
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "treatments_id"))
    private Set<Treatment> treatments;
    
    @OneToMany(mappedBy = "author", targetEntity = Message.class)
    private List<Message> sentMessages;
    
    @OneToMany(mappedBy = "recipient", targetEntity = Message.class)
    private List<Message> receivedMessages;
    
    @OneToMany(mappedBy = "createdByUser", targetEntity = Treatment.class)
    List<Treatment> createdTreatments; 
    
    @OneToMany(mappedBy = "addedByUser", targetEntity = Image.class)
    List<Image> addedImages;
    
    @OneToMany(mappedBy = "createdByUser", targetEntity = HealthCondition.class)
    private List<HealthCondition> createdHealthConditions;
    
    public User() {
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
    
    public Integer getAge() {
        return age;
    }
    
    public void setAge(Integer age) {
        this.age = age;
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
    
    public List<Role> getRoles() {
        return roles;
    }
    
    public void setRoles(List<Role> roles) {
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
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }
    
    public List<Treatment> getCreatedTreatments() {
        return createdTreatments;
    }
    
    public void setCreatedTreatments(List<Treatment> addedTreatments) {
        this.createdTreatments = addedTreatments;
    }
    
    public List<Image> getAddedImages() {
        return addedImages;
    }
    
    public void setAddedImages(List<Image> addedImages) {
        this.addedImages = addedImages;
    }
    
}
