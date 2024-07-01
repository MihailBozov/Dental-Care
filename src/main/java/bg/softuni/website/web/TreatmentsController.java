package bg.softuni.website.web;

import bg.softuni.website.models.entities.Treatment;
import bg.softuni.website.services.TreatmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class TreatmentsController {
    TreatmentService treatmentService;
    
    public TreatmentsController(TreatmentService treatmentService) {
        this.treatmentService = treatmentService;
    }
    
    @GetMapping("/treatments")
    public String treatments(Model model) {
        List<Treatment> treatments = treatmentService.getAllTreatments();
        model.addAttribute("treatment", treatments);
        
        
        return "treatmentsList.html";
    }
}
