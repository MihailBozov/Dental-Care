package bg.softuni.website.services;

import bg.softuni.website.models.dtos.EditTreatmentDto;
import bg.softuni.website.models.dtos.NewTreatmentDto;
import bg.softuni.website.models.dtos.TreatmentDto;
import bg.softuni.website.models.entities.Image;
import bg.softuni.website.models.entities.Treatment;
import bg.softuni.website.repositories.TreatmentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TreatmentServiceTest {
    
    private TreatmentService treatmentService;
    
    @Mock
    private TreatmentRepository treatmentRepository;
    
    @Mock
    private ImageService imageService;
    
    @Mock
    private ModelMapper modelMapper;
    
    @BeforeEach
    void setUp() {
        treatmentService = new TreatmentService(treatmentRepository, imageService, modelMapper);
    }
    
    @Test
    void getAllTreatmentDtos() {
        Treatment treatment1 = new Treatment();
        Treatment treatment2 = new Treatment();
        
        when(treatmentRepository.findAll())
                .thenReturn(Arrays.asList(treatment1, treatment2));
        
        TreatmentDto treatmentDto1 = new TreatmentDto();
        TreatmentDto treatmentDto2 = new TreatmentDto();
        when(modelMapper.map(treatment1, TreatmentDto.class))
                .thenReturn(treatmentDto1);
        when(modelMapper.map(treatment2, TreatmentDto.class))
                .thenReturn(treatmentDto2);
        
        List<TreatmentDto> treatmentDtos = treatmentService.getAllTreatmentDtos();
        
        Assertions.assertEquals(2, treatmentDtos.size());
        
        verify(treatmentRepository, times(1))
                .findAll();
        verify(modelMapper, times(1))
                .map(treatment1, TreatmentDto.class);
        verify(modelMapper, times(1))
                .map(treatment2, TreatmentDto.class);
    }
    
    @Test
    void newTreatment() throws IOException {
        NewTreatmentDto newTreatmentDto = new NewTreatmentDto();
        MultipartFile file = mock(MultipartFile.class);
        newTreatmentDto.setImage(file);
        
        Image image = new Image();
        when(imageService.saveImage(file, "formNewTreatment"))
                .thenReturn(image);
        
        Treatment treatment = new Treatment();
        when(modelMapper.map(newTreatmentDto, Treatment.class))
                .thenReturn(treatment);
        
        treatmentService.newTreatment(newTreatmentDto);
        
        verify(imageService, times(1))
                .saveImage(file, "formNewTreatment");
        verify(treatmentRepository, times(1))
                .saveAndFlush(treatment);
        Assertions.assertEquals(image, treatment.getImage());
    }
    
    @Test
    void deleteTreatment() throws IOException {
        long id = 1L;
        Treatment treatment = new Treatment();
        Image image = new Image();
        treatment.setId(id);
        treatment.setImage(image);
        
        when(treatmentRepository.findById(id))
                .thenReturn(Optional.of(treatment));
        
        boolean result = treatmentService.deleteTreatment(id);
        Assertions.assertTrue(result);
        
        verify(treatmentRepository, times(1))
                .findById(id);
        verify(treatmentRepository, times(1))
                .delete(treatment);
        verify(imageService, times(1))
                .deleteImage(image);
    }
    
    @Test
    void deleteTreatmentNotFound() throws IOException {
        long id = 1L;
        when(treatmentRepository.findById(id))
                .thenReturn(Optional.empty());
        boolean result = treatmentService.deleteTreatment(id);
        
        Assertions.assertFalse(result);
        verify(treatmentRepository, times(1))
                .findById(id);
        verify(treatmentRepository, never())
                .delete(any(Treatment.class));
        verify(imageService, never())
                .deleteImage(any(Image.class));
        
    }
    
    @Test
    void editTreatmentNotFound() throws IOException {
        long id = 1L;
        EditTreatmentDto editTreatmentDto = new EditTreatmentDto();
        when(treatmentRepository.findById(id)).thenReturn(Optional.empty());
        
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            treatmentService.editTreatment(editTreatmentDto, id);
        });
        
        Assertions.assertEquals("Treatment with id " + id + " not found", exception.getMessage());
        
        verify(treatmentRepository, times(1)).findById(id);
        verify(imageService, never()).updateImage(any(), any(), any());
    }
    
    @Test
    void editTreatment() throws IOException {
        long id = 1L;
        MultipartFile file = mock(MultipartFile.class);
        
        EditTreatmentDto editTreatmentDto = new EditTreatmentDto();
        editTreatmentDto.setName("treatmentName");
        editTreatmentDto.setDescription("treatmentDescription");
        editTreatmentDto.setPrice(10);
        editTreatmentDto.setImage(file);
        
        Treatment treatment = new Treatment();
        Image oldImage = new Image();
        treatment.setImage(oldImage);
        
        when(treatmentRepository.findById(id))
                .thenReturn(Optional.of(treatment));
        
        Image newImage = new Image();
        when(imageService.updateImage(file, "formNewTreatment", Optional.of(oldImage)))
                .thenReturn(newImage);
        
        boolean result = treatmentService.editTreatment(editTreatmentDto, id);
        Assertions.assertTrue(result);
        
        verify(treatmentRepository, times(1))
                .findById(id);
        verify(imageService, times(1))
                .updateImage(file, "formNewTreatment", Optional.of(oldImage));
        verify(treatmentRepository, times(1))
                .saveAndFlush(treatment);
        
        Assertions.assertEquals(newImage, treatment.getImage());
        Assertions.assertEquals(editTreatmentDto.getName(), treatment.getName());
        Assertions.assertEquals(editTreatmentDto.getDescription(), treatment.getDescription());
        Assertions.assertEquals(editTreatmentDto.getPrice(), treatment.getPrice());
        
    }
    
    @Test
    void getTreatment() {
        long id = 1L;
        Treatment treatment = new Treatment();
        treatment.setId(id);
        when(treatmentRepository.findById(id))
                .thenReturn(Optional.of(treatment));
        
        Treatment treatmentTest = treatmentService.getTreatment(id);
        Assertions.assertEquals(treatmentTest, treatment);
        
        verify(treatmentRepository, times(1))
                .findById(id);
        
    }
    
    @Test
    void getEditTreatmentDto() {
        long id = 1L;
        Treatment treatment = new Treatment();
        treatment.setId(id);
        treatment.setName("treatmentName");
        treatment.setDescription("treatmentDescription");
        treatment.setId(id);
        
        when(treatmentRepository.findById(id))
                .thenReturn(Optional.of(treatment));
        
        EditTreatmentDto editTreatmentDto = new EditTreatmentDto();
        editTreatmentDto.setName(treatment.getName());
        editTreatmentDto.setDescription(treatment.getDescription());
        editTreatmentDto.setId(id);
        when(modelMapper.map(treatment, EditTreatmentDto.class))
                .thenReturn(editTreatmentDto);
        
        EditTreatmentDto editTreatmentDtoTest = treatmentService.getEditTreatmentDto(id);
        Assertions.assertEquals(treatment.getName(), editTreatmentDtoTest.getName());
        Assertions.assertEquals(treatment.getDescription(), editTreatmentDtoTest.getDescription());
        
        verify(treatmentRepository, times(1))
                .findById(id);
        verify(modelMapper, times(1))
                .map(treatment, EditTreatmentDto.class);
       
    }
    
    @Test
    void getAllTreatmentsFiltered() {
        long id = 1L;
        Treatment treatment1 = new Treatment();
        treatment1.setId(1);
        Treatment treatment2 = new Treatment();
        treatment2.setId(2);
        Treatment treatment3 = new Treatment();
        treatment3.setId(3);
        when(this.treatmentRepository.findAllByIdNot(id))
                .thenReturn(List.of(treatment2, treatment3));
        
        List<Treatment> treatmentsTest = treatmentService.getAllTreatmentsFiltered(id);
        Assertions.assertEquals(2, treatmentsTest.size());
        Assertions.assertFalse(treatmentsTest.contains(treatment1));
        verify(treatmentRepository, times(1))
                .findAllByIdNot(id);
    }
}