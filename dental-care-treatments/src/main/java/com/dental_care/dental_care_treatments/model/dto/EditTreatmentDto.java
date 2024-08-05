package com.dental_care.dental_care_treatments.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;


@Getter
@Setter
public class EditTreatmentDto {
    
    private long id;
    
    @NotBlank(message = "{treatment.name.cannot.be.blank}")
    private String name;
    
    private String description;
    
    @Positive(message = "{treatment.price.must.be.positive.number}")
    private double price;
    
//    private long createdByUserEntityId;
    
//    private MultipartFile image;
}
