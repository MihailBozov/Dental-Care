package bg.softuni.website.web;

import bg.softuni.website.models.dtos.TeamDto;
import bg.softuni.website.services.TeamService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.awaitility.Awaitility.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class TeamControllerTest {
    
    @Autowired
    MockMvc mockMvc;
    
    @Autowired
    WebApplicationContext webApplicationContext;
    
    @MockBean
    private TeamService teamService;
    
    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    
    @Test
    void initAllTeamMembers() throws Exception {
        TeamDto member1 = new TeamDto();
        member1.setFirstName("Jessica");
        member1.setPictureUrl("pictures/jessica.jpg");
        
        TeamDto member2 = new TeamDto();
        member2.setFirstName("Bobi");
        member2.setPictureUrl("pictures/bobi.jpg");
        
        List<TeamDto> members = List.of(member1, member2);
        
        given(teamService.getAllTeamMembers()).willReturn(members);
        
        
        mockMvc.perform(get("/team"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("allTeamMembers"))
                .andExpect(model().attribute("allTeamMembers", members));
        
    }
    
    @Test
    void team() throws Exception {
      
        mockMvc.perform(get("/team"))
                .andExpect(status().isOk())
                .andExpect(view().name("team-page"))
                .andExpect(model().attributeExists("currentPage"))
                .andExpect(model().attribute("currentPage", "teamPage"));
    }
}