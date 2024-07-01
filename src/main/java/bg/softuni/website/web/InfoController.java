package bg.softuni.website.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
