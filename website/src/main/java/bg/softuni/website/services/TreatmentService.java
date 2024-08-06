package bg.softuni.website.services;

import bg.softuni.website.models.dtos.NewTreatmentDto;
import bg.softuni.website.models.dtos.EditTreatmentDto;
import bg.softuni.website.models.dtos.TreatmentDto;
import bg.softuni.website.models.entities.Image;
import bg.softuni.website.models.entities.Treatment;
import bg.softuni.website.repositories.TreatmentRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TreatmentService {
    private final Logger logger = LoggerFactory.getLogger(TreatmentService.class);
    private final TreatmentRepository treatmentRepository;
    private final ImageService imageService;
    private final ModelMapper modelMapper;

    @Autowired
    public TreatmentService(TreatmentRepository treatmentRepository, ImageService imageService, ModelMapper modelMapper) {
        this.treatmentRepository = treatmentRepository;
        this.imageService = imageService;
        this.modelMapper = modelMapper;
    }

    @Cacheable("treatments")
    public List<TreatmentDto> getAllTreatmentDtos() {
        logger.warn("getAllTreatmentDtos() is annotated with @Cacheable and is executed !");
        List<Treatment> allTreatments = treatmentRepository.findAll();
        List<TreatmentDto> treatmentDtos = new ArrayList<>();

        for (Treatment treatment : allTreatments) {
            TreatmentDto treatmentDto = this.modelMapper.map(treatment, TreatmentDto.class);
            treatmentDtos.add(treatmentDto);
        }
        return treatmentDtos;
    }

    @CacheEvict(value = "treatments", allEntries = true)
    public void newTreatment(NewTreatmentDto newTreatmentDto) throws IOException {
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

    @CacheEvict(value = "treatments", allEntries = true)
    @Transactional(rollbackOn = Exception.class)
    public boolean deleteTreatment(long id) throws IOException {
        Optional<Treatment> treatment = this.treatmentRepository.findById(id);
        if (treatment.isPresent()) {
            Image image = treatment.get().getImage();
            this.treatmentRepository.delete(treatment.get());
            this.imageService.deleteImage(image);
            return true;
        }
        return false;
    }

    @CacheEvict(value = "treatments", allEntries = true)
    @Transactional(rollbackOn = Exception.class)
    public boolean editTreatment(EditTreatmentDto editTreatmentDto, long id) throws IOException {

        Treatment treatment = getTreatment(id);

        String formName = "formNewTreatment";
        MultipartFile file = editTreatmentDto.getImage();


        Image image = this.imageService.updateImage(file, formName, Optional.of(treatment.getImage()));

        treatment.setName(editTreatmentDto.getName());
        treatment.setDescription(editTreatmentDto.getDescription());
        treatment.setPrice(editTreatmentDto.getPrice());
        treatment.setImage(image);

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
