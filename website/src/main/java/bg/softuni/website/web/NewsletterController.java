package bg.softuni.website.web;

import bg.softuni.website.models.dtos.NewsletterDto;
import bg.softuni.website.services.NewsletterService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class NewsletterController {
    
    private NewsletterService newsletterService;
    
    public NewsletterController(NewsletterService newsletterService) {
        this.newsletterService = newsletterService;
    }
    
    @PostMapping("/newsletter")
    public String newsletter(@Valid NewsletterDto newsletterDto,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors().getFirst().toString());
            redirectAttributes.addFlashAttribute("newsletterDto", newsletterDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.newsletterDto", bindingResult);
            return "redirect:/";
        }
        redirectAttributes.addFlashAttribute("successNewsletter", "Great! You're now subscribed to our newsletter.");
        
        this.newsletterService.persistEmail(newsletterDto.getNewsletterEmail());
        return "redirect:/";
    }
}
