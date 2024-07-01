package bg.softuni.website.services;

import bg.softuni.website.models.dtos.TreatmentDto;
import bg.softuni.website.models.entities.Treatment;
import bg.softuni.website.repositories.TreatmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TreatmentService {
    
    TreatmentRepository treatmentRepository;
    private ModelMapper modelMapper;
    
    @Autowired
    public TreatmentService(TreatmentRepository treatmentRepository, ModelMapper modelMapper) {
        this.treatmentRepository = treatmentRepository;
        this.modelMapper = modelMapper;
    }
    
    public List<TreatmentDto> getAllTreatments() {
        List<Treatment> allTreatments = treatmentRepository.findAll();
        List<TreatmentDto> treatmentDtos = new ArrayList<>();
        
        for (Treatment treatment : allTreatments) {
            TreatmentDto treatmentDto = this.modelMapper.map(treatment, TreatmentDto.class);
            treatmentDto.setImageUrl(treatment.getImage().getUrl());
            treatmentDtos.add(treatmentDto);
        }
        return treatmentDtos;
    }
}
