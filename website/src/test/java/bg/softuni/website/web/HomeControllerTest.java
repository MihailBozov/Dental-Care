package bg.softuni.website.web;

import bg.softuni.website.models.dtos.TeamDto;
import bg.softuni.website.models.dtos.TestimonialDto;
import bg.softuni.website.models.dtos.TreatmentDto;
import bg.softuni.website.services.TeamService;
import bg.softuni.website.services.TestimonialService;
import bg.softuni.website.services.TreatmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class HomeControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private TeamService teamService;
    
    @MockBean
    private TreatmentService treatmentService;
    
    @MockBean
    private TestimonialService testimonialService;
    
    @Autowired
    private WebApplicationContext webApplicationContext;
    
    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        when(treatmentService.getAllTreatmentDtos()).thenReturn(List.of(new TreatmentDto(), new TreatmentDto()));
        when(teamService.getAllTeamMembers()).thenReturn(List.of(new TeamDto(), new TeamDto()));
        when(testimonialService.getAllTestimonials()).thenReturn(List.of(new TestimonialDto(), new TestimonialDto()));
    }
    
    @Test
    void home() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("allTreatments"))
                .andExpect(model().attributeExists("allTestimonials"))
                .andExpect(model().attributeExists("allTeamMembers"))
                .andExpect(model().attributeExists("currentPage"));
    }
    
    @Test
    void resetPasswordDto() {
    }
}