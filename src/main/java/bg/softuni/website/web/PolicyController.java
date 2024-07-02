package bg.softuni.website.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PolicyController {
    
    @GetMapping("/policies")
    public String info(Model model) {
        model.addAttribute("currentPage", "policiesPage");
        return "policiesPage";
    }
    
    @GetMapping("/policies/shipping")
    public String shippingInfo() {
        return "shippingPoliciesPage";
    }
    
    @GetMapping("/policies/returnsAndExchanges")
    public String returnsAndExchangePage() {
        return "returnsAndExchangePage";
    }
    
    @GetMapping("/policies/payments")
    public String payment() {
        return "paymentsPage";
    }
    
}
