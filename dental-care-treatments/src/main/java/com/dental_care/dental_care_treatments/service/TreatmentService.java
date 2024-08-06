package com.dental_care.dental_care_treatments.service;

import com.dental_care.dental_care_treatments.model.dto.EditTreatmentDto;
import com.dental_care.dental_care_treatments.model.dto.NewTreatmentDto;
import com.dental_care.dental_care_treatments.model.dto.TreatmentDto;
import com.dental_care.dental_care_treatments.model.entity.Treatment;
import com.dental_care.dental_care_treatments.repository.TreatmentRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TreatmentService {

    private final TreatmentRepository treatmentRepository;
    private final ModelMapper modelMapper;
    private final WebClient webClient;
    
    @Autowired
    public TreatmentService(TreatmentRepository treatmentRepository, ModelMapper modelMapper, WebClient.Builder webClientBuilder) {
        this.treatmentRepository = treatmentRepository;
        this.modelMapper = modelMapper;
        this.webClient = webClientBuilder.baseUrl("http://localhost:8081").build();
    }
    
    public List<TreatmentDto> getAllTreatmentDtos() {
        List<Treatment> allTreatments = treatmentRepository.findAll();
        List<TreatmentDto> treatmentDtos = new ArrayList<>();
        
        for (Treatment treatment : allTreatments) {
            TreatmentDto treatmentDto = this.modelMapper.map(treatment, TreatmentDto.class);
            treatmentDtos.add(treatmentDto);
        }
        return treatmentDtos;
    }
    

    public void newTreatment(NewTreatmentDto newTreatmentDto) throws IOException {
        
        Treatment treatment = this.modelMapper.map(newTreatmentDto, Treatment.class);
        this.treatmentRepository.saveAndFlush(treatment);
    }
    
    
    @Transactional(rollbackOn = Exception.class)
    public boolean deleteTreatment(long id) throws IOException {
        Optional<Treatment> treatment = this.treatmentRepository.findById(id);
        if (treatment.isPresent()) {
            this.treatmentRepository.delete(treatment.get());
            return true;
        }
        return false;
    }
    
    
    @Transactional(rollbackOn = Exception.class)
    public boolean editTreatment(EditTreatmentDto editTreatmentDto, long id) throws IOException {
        
        Treatment treatment = getTreatment(id);
        
        String formName = "formNewTreatment";
//        MultipartFile file = editTreatmentDto.getImage();
        
        treatment.setName(editTreatmentDto.getName());
        treatment.setDescription(editTreatmentDto.getDescription());
        treatment.setPrice(editTreatmentDto.getPrice());
        
        this.treatmentRepository.saveAndFlush(treatment);
        return true;
        
    }
    
    
    public Treatment getTreatment(Long id) {
        return this.treatmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Treatment with id " + id + " not found"));
    }
    
    
    public EditTreatmentDto getEditTreatmentDto(Long id) {
        Treatment treatment = this.treatmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Treatment with id " + id + " not found"));
        return this.modelMapper.map(treatment, EditTreatmentDto.class);
    }
    
    
    public List<Treatment> getAllTreatmentsFiltered(Long id) {
        List<Treatment> treatments = this.treatmentRepository.findAllByIdNot(id);
        return treatments;
    }
}
