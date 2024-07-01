package bg.softuni.website.web;

import bg.softuni.website.models.dtos.TreatmentDto;
import bg.softuni.website.models.entities.Treatment;
import bg.softuni.website.services.TreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class TreatmentsController {
    TreatmentService treatmentService;
    
    @Autowired
    public TreatmentsController(TreatmentService treatmentService) {
        this.treatmentService = treatmentService;
    }
    
    @ModelAttribute("allTreatments")
    public List<TreatmentDto> initTreatmentDto() {
        return this.treatmentService.getAllTreatments();
    }
    
    @GetMapping("/treatments")
    public String treatments() {
        
        return "treatmentsPage";
    }
}
