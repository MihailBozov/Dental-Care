package bg.softuni.website.web;

import bg.softuni.website.models.dtos.TeamDto;
import bg.softuni.website.models.dtos.TreatmentDto;
import bg.softuni.website.services.TeamService;
import bg.softuni.website.services.TreatmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {
    
    private TeamService teamService;
    private TreatmentService treatmentService;
    
    public HomeController(TeamService teamService, TreatmentService treatmentService) {
        this.teamService = teamService;
        this.treatmentService = treatmentService;
    }
    
    @ModelAttribute("allTreatments")
    public List<TreatmentDto> initTreatmentDto() {
        return this.treatmentService.getAllTreatments();
    }
    
    @ModelAttribute("allTeamMembers")
    public List<TeamDto> initAllTeamMembers() {
        return this.teamService.getAllTeamMembers();
    }
    
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("currentPageHome", "homePage");
        return "index";
    }
}
