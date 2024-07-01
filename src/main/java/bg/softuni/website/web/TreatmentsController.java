package bg.softuni.website.web;

import bg.softuni.website.models.dtos.NewTreatmentDto;
import bg.softuni.website.models.dtos.TreatmentDto;
import bg.softuni.website.models.entities.Treatment;
import bg.softuni.website.services.TreatmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String treatments(Model model) {
        model.addAttribute("currentPage", "treatmentsPage");
        return "treatmentsPage";
    }

//    --------------------------------------------------------------------
    
    
    @ModelAttribute("newTreatmentDto")
    public NewTreatmentDto initNewTreatmentDto() {
        return new NewTreatmentDto();
    }

    
    
    @PostMapping("/treatments/newTreatment")
    public String treatment(@Valid NewTreatmentDto newTreatmentDto,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes,
                            Model model) {
        
        model.addAttribute("newTreatmentDto", initNewTreatmentDto());
        
        return "redirect:/treatments";
    }
    
}
