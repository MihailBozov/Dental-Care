package bg.softuni.website.web;

import bg.softuni.website.models.dtos.TeamDto;
import bg.softuni.website.models.dtos.TestimonialDto;
import bg.softuni.website.models.dtos.TreatmentDto;
import bg.softuni.website.services.NewsletterService;
import bg.softuni.website.services.TeamService;
import bg.softuni.website.services.TestimonialService;
import bg.softuni.website.services.TreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {
    
    private TeamService teamService;
    private TreatmentService treatmentService;
    private TestimonialService testimonialService;
    
    @Autowired
    public HomeController(TeamService teamService, TreatmentService treatmentService, TestimonialService testimonialService, NewsletterService newsletterService) {
        this.teamService = teamService;
        this.treatmentService = treatmentService;
        this.testimonialService = testimonialService;
    }
    
    @ModelAttribute("allTreatments")
    public List<TreatmentDto> initTreatmentDto() {
        return this.treatmentService.getAllTreatments();
    }
    
    @ModelAttribute("allTeamMembers")
    public List<TeamDto> initAllTeamMembers() {
        return this.teamService.getAllTeamMembers();
    }
    
    @ModelAttribute("allTestimonials")
    public List<TestimonialDto> initTestimonialDto() {
        return this.testimonialService.getAllTestimonials();
    }
    
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("currentPage", "homePage");
        return "index";
    }

   
}
