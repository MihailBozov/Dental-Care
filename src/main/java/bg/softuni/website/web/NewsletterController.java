//package bg.softuni.website.web;
//
//import bg.softuni.website.models.dtos.NewsletterDto;
//import bg.softuni.website.services.NewsletterService;
//import jakarta.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.Banner;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//@Controller
//public class NewsletterController {
//    
//    private NewsletterService newsletterService;
//    
//    @Autowired
//    public NewsletterController(NewsletterService newsletterService) {
//        this.newsletterService = newsletterService;
//    }
//    
//    @ModelAttribute("newsletterDto") 
//    public NewsletterDto newsletterDto() {
//        return new NewsletterDto();
//    }
//    
//    @PostMapping("/")
//    public String newsletter(@Valid NewsletterDto newsletterDto,
//                             BindingResult bindingResult,
//                             RedirectAttributes redirectAttributes,
//                             Model model) {
//        
//        if (bindingResult.hasErrors()) {
//            redirectAttributes.addFlashAttribute("newsletterDto", newsletterDto);
//            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.newsletterDto", bindingResult);
//            return "redirect:/#footer";
//        }
//        redirectAttributes.addFlashAttribute("success", "Great! You're now subscribed to our newsletter.");
//        
//        this.newsletterService.persistEmail(newsletterDto.getNewsletterEmail());
//        
//        return "redirect:/#footer";
//    }
//}
