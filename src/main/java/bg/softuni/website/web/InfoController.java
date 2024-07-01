package bg.softuni.website.web;

import bg.softuni.website.models.dtos.NewsletterDto;
import bg.softuni.website.services.NewsletterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class InfoController {
    
    @GetMapping("/info")
    public String info(Model model) {
        model.addAttribute("currentPage", "infoPage");
        return "infoPage";
    }
    
    @GetMapping("/info/shippingInfo")
    public String shippingInfo() {
        return "shippingInfoPage";
    }
    
    @GetMapping("/info/returnsAndExchanges")
    public String returnsAndExchangePage() {
        return "returnsAndExchangePage";
    }
    
    @GetMapping("/info/payment")
    public String payment() {
        return "paymentsPage";
    }
    
}
