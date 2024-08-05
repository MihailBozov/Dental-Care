package com.dental_care.dental_care_treatments.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "treatments")
public class Treatment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(nullable = false, unique = true)
    private String name;
    
    @Column(length = 2000)
    private String description;
    
    @Basic
    private double price;
    

//    @Column(name = "created_by_user_id")
//    private long createdByUserId;
    
//    @Column(name = "image_id")
//    private long imageId;
    
    @Column(name = "creation_date")
    LocalDateTime creationDate;
    
    public Treatment() {}
    
    @PrePersist
    protected void onCreate() {
        this.creationDate = LocalDateTime.now();
    }
    
}
