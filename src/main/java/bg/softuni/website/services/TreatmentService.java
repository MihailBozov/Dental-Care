package bg.softuni.website.services;

import bg.softuni.website.models.dtos.NewTreatmentDto;
import bg.softuni.website.models.dtos.TreatmentDto;
import bg.softuni.website.models.entities.Image;
import bg.softuni.website.models.entities.Treatment;
import bg.softuni.website.repositories.ImageRepository;
import bg.softuni.website.repositories.TreatmentRepository;
import bg.softuni.website.sessions.UserSession;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class TreatmentService {
    
    private TreatmentRepository treatmentRepository;
    private ImageService imageService;
    private ModelMapper modelMapper;
    private UserSession userSession;
    
    @Autowired
    public TreatmentService(TreatmentRepository treatmentRepository, ImageService imageService, ModelMapper modelMapper, UserSession userSession) {
        this.treatmentRepository = treatmentRepository;
        this.imageService = imageService;
        this.modelMapper = modelMapper;
        this.userSession = userSession;
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
    
    public void addNewTreatment(NewTreatmentDto newTreatmentDto) throws IOException {
        String formName = "formNewTreatment";
        MultipartFile file = newTreatmentDto.getImage();
        
        Image image = this.imageService.saveImage(file, formName);
        
        Treatment treatment = this.modelMapper.map(newTreatmentDto, Treatment.class);
        treatment.setImage(image);
        treatment.setCreatedByUser(userSession.getUser());
        treatment.setImage(image);
        
        this.treatmentRepository.saveAndFlush(treatment);
    }
}
