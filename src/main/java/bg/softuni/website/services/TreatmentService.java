package bg.softuni.website.services;

import bg.softuni.website.models.dtos.NewTreatmentDto;
import bg.softuni.website.models.dtos.TreatmentDto;
import bg.softuni.website.models.entities.Image;
import bg.softuni.website.models.entities.Treatment;
import bg.softuni.website.repositories.TreatmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TreatmentService {
    
    private TreatmentRepository treatmentRepository;
    private ImageService imageService;
    private ModelMapper modelMapper;
    
    @Autowired
    public TreatmentService(TreatmentRepository treatmentRepository, ImageService imageService, ModelMapper modelMapper) {
        this.treatmentRepository = treatmentRepository;
        this.imageService = imageService;
        this.modelMapper = modelMapper;
    }
    
    public List<TreatmentDto> getAllTreatments() {
        List<Treatment> allTreatments = treatmentRepository.findAll();
        List<TreatmentDto> treatmentDtos = new ArrayList<>();
        
        for (Treatment treatment : allTreatments) {
            TreatmentDto treatmentDto = this.modelMapper.map(treatment, TreatmentDto.class);
            treatmentDtos.add(treatmentDto);
        }
        return treatmentDtos;
    }
    
    public void addNewTreatment(NewTreatmentDto newTreatmentDto) throws IOException {
        String formName = "formNewTreatment";
        MultipartFile file = newTreatmentDto.getImage();
        
        Image image = this.imageService.saveImage(file, formName);
        
        Treatment treatment = this.modelMapper.map(newTreatmentDto, Treatment.class);
        treatment.setImage(image);
        //TODO
//        treatment.setCreatedByUserEntity(userSession.getUser());
        treatment.setImage(image);
        
        this.treatmentRepository.saveAndFlush(treatment);
    }
    
    public boolean deleteTreatment(Long id) throws IOException {
        Optional<Treatment> treatment = this.treatmentRepository.findById(id);
        if (treatment.isPresent()) {
            this.imageService.deleteImage(treatment.get().getImage());
            this.treatmentRepository.delete(treatment.get());
            return true;
        }
        return false;
    }
    
}
