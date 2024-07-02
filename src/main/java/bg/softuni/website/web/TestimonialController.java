package bg.softuni.website.web;

import bg.softuni.website.models.dtos.TestimonialDto;
import bg.softuni.website.services.TestimonialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class TestimonialController {
    
    private TestimonialService testimonialService;
    
    @Autowired
    public TestimonialController(TestimonialService testimonialService) {
        this.testimonialService = testimonialService;
    }
    
    @ModelAttribute("allTestimonials")
    public List<TestimonialDto> initTestimonialDto() {
        return this.testimonialService.getAllTestimonials();
    }
    
    @GetMapping("/testimonials")
    public String testimonials(Model model) {
        model.addAttribute("currentPage", "testimonialsPage");
        return "testimonials-page";
    }
}
