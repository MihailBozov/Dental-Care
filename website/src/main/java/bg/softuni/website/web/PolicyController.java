package bg.softuni.website.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PolicyController {
    
    @GetMapping("/policies")
    public String info(Model model) {
        model.addAttribute("currentPage", "policiesPage");
        return "policies-page";
    }
    
    @GetMapping("/policies/shipping")
    public String shippingInfo() {
        return "shipping-policies-page";
    }
    
    @GetMapping("/policies/returnsAndExchanges")
    public String returnsAndExchangePage() {
        return "returns-and-exchanges-page";
    }
    
    @GetMapping("/policies/payments")
    public String payment() {
        return "payments-page";
    }
    
}
