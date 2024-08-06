package com.dental_care.dental_care_treatments.web;

import com.dental_care.dental_care_treatments.model.dto.EditTreatmentDto;
import com.dental_care.dental_care_treatments.model.dto.NewTreatmentDto;
import com.dental_care.dental_care_treatments.model.dto.TreatmentDto;
import com.dental_care.dental_care_treatments.model.entity.Treatment;
import com.dental_care.dental_care_treatments.repository.TreatmentRepository;
import com.dental_care.dental_care_treatments.service.TreatmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/treatments")
public class TreatmentController {
    
    private final TreatmentService treatmentService;
    private static Logger LOGGER = LoggerFactory.getLogger(TreatmentController.class);
    
    @Autowired
    public TreatmentController(TreatmentService treatmentService) {
        this.treatmentService = treatmentService;
    }
    
    @GetMapping
    public ResponseEntity<List<TreatmentDto>> getAllTreatments() {
        
        return ResponseEntity.ok(treatmentService.getAllTreatmentDtos());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Treatment> getTreatment(@PathVariable long id) {
        return ResponseEntity.ok(this.treatmentService.getTreatment(id));
    }
    
    @PostMapping
    public ResponseEntity<TreatmentDto> createTreatment(@RequestBody NewTreatmentDto newTreatmentDto) throws IOException {
        LOGGER.info("Going to create new treatment");
        treatmentService.newTreatment(newTreatmentDto);
        return ResponseEntity.ok().build();
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Boolean> updateTreatment(@RequestBody EditTreatmentDto editTreatmentDto, @PathVariable("id") long id) throws IOException {
       return ResponseEntity.ok(this.treatmentService.editTreatment(editTreatmentDto, id));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteTreatment(@PathVariable long id) throws IOException {
        return ResponseEntity.ok(treatmentService.deleteTreatment(id));
    }
}
