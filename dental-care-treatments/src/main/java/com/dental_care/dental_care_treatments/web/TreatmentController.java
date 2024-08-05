package com.dental_care.dental_care_treatments.web;

import com.dental_care.dental_care_treatments.model.dto.NewTreatmentDto;
import com.dental_care.dental_care_treatments.model.dto.TreatmentDto;
import com.dental_care.dental_care_treatments.model.entity.Treatment;
import com.dental_care.dental_care_treatments.repository.TreatmentRepository;
import com.dental_care.dental_care_treatments.service.TreatmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/treatments")
public class TreatmentController {
    
    private final TreatmentService treatmentService;
    private static Logger LOGGER = LoggerFactory.getLogger(TreatmentController.class);
    
    @Autowired
    public TreatmentController(TreatmentService treatmentService) {
        this.treatmentService = treatmentService;
    }
    
    @PostMapping
    public ResponseEntity<TreatmentDto> createTreatment(@RequestBody NewTreatmentDto newTreatmentDto) throws IOException {
        LOGGER.info("Going to create new treatment");
        treatmentService.newTreatment(newTreatmentDto);
        return ResponseEntity.ok().build();
    }
}
