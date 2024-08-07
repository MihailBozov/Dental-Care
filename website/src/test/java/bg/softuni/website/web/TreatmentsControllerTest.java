package bg.softuni.website.web;

import bg.softuni.website.models.dtos.NewTreatmentDto;
import bg.softuni.website.models.dtos.TreatmentDto;
import bg.softuni.website.services.TreatmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class TreatmentsControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    WebApplicationContext webApplicationContext;
    
    @MockBean
    private TreatmentService treatmentService;
    
    
    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    
    @Test
    void initTreatmentDto() throws Exception {
        TreatmentDto treatment1 = new TreatmentDto();
        treatment1.setName("Treatment One Test");
        treatment1.setDescription("Test Description One Test");
        
        TreatmentDto treatment2 = new TreatmentDto();
        treatment2.setName("Treatment Two Test");
        treatment2.setDescription("Test Description Two Test");
        
        List<TreatmentDto> treatments = List.of(treatment1, treatment2);
        
        given(this.treatmentService.getAllTreatmentDtos()).willReturn(treatments);
        
        mockMvc.perform(get("/treatments"))
                .andExpect(status().isOk())
                .andExpect(view().name("treatments-page"))
                .andExpect(model().attributeExists("allTreatments"))
                .andExpect(model().attribute("allTreatments", treatments));
        
    }
    
    
    @Test
    void treatment() throws Exception {
        MockMultipartFile image = new MockMultipartFile("image", "image.jpg", MediaType.IMAGE_JPEG_VALUE, "image content".getBytes());
        NewTreatmentDto newTreatmentDto = new NewTreatmentDto();
        newTreatmentDto.setName("New Treatment");
        newTreatmentDto.setDescription("Description");
        newTreatmentDto.setPrice(100.0);
        newTreatmentDto.setImage(image);
        
        mockMvc.perform(multipart("/treatments/newTreatment")
                        .file(image)
                        .param("name", newTreatmentDto.getName())
                        .param("description", newTreatmentDto.getDescription())
                        .param("price", String.valueOf(newTreatmentDto.getPrice())))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/treatments?success=true"));
        
    }
    
    @Test
    void treatmentShouldThrowException() throws Exception {
        MockMultipartFile image = new MockMultipartFile("image", "image.jpg", MediaType.IMAGE_JPEG_VALUE, "image content".getBytes());
        
        this.mockMvc.perform(MockMvcRequestBuilders.multipart("/treatments/newTreatment")
                        .file(image)
                        .param("name", "")
                        .param("description", "Description")
                        .param("price", "100.0"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/treatments"))
                .andExpect(MockMvcResultMatchers.flash().attributeExists("org.springframework.validation.BindingResult.newTreatmentDto"))
                .andExpect(MockMvcResultMatchers.flash().attributeExists("newTreatmentDto"));
    }
    
    
    @Test
    void deleteTreatment() {
    }
    
    @Test
    void initEditTreatmentDto() {
    }
    
    @Test
    void editTreatment() {
    }
    
    @Test
    void testEditTreatment() {
    }
}