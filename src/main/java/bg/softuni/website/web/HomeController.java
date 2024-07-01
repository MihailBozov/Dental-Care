package bg.softuni.website.web;

import bg.softuni.website.models.dtos.NewsletterDto;
import bg.softuni.website.models.dtos.TeamDto;
import bg.softuni.website.models.dtos.TestimonialDto;
import bg.softuni.website.models.dtos.TreatmentDto;
import bg.softuni.website.services.NewsletterService;
import bg.softuni.website.services.TeamService;
import bg.softuni.website.services.TestimonialService;
import bg.softuni.website.services.TreatmentService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {
    
    private TeamService teamService;
    private TreatmentService treatmentService;
    private TestimonialService testimonialService;
    private NewsletterService newsletterService;
    
    
    public HomeController(TeamService teamService, TreatmentService treatmentService, TestimonialService testimonialService, NewsletterService newsletterService) {
        this.teamService = teamService;
        this.treatmentService = treatmentService;
        this.testimonialService = testimonialService;
        this.newsletterService = newsletterService;
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
    
    @ModelAttribute("newsletterDto")
    public NewsletterDto newsletterDto() {
        return new NewsletterDto();
    }

    
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("currentPage", "homePage");
        model.addAttribute("currentPageUrl", "/");
        return "index";
    }
    
//    -------------------     footer     -------------------
    

    @PostMapping("/")
    public String newsletter(@Valid NewsletterDto newsletterDto,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("newsletterDto", newsletterDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.newsletterDto", bindingResult);
            return "redirect:/#footer";
        }
        redirectAttributes.addFlashAttribute("success", "Great! You're now subscribed to our newsletter.");

        this.newsletterService.persistEmail(newsletterDto.getNewsletterEmail());
        return "redirect:/#footer";
    }
}
