package bg.softuni.website.web;

import bg.softuni.website.models.dtos.NewTreatmentDto;
import bg.softuni.website.models.dtos.TreatmentDto;
import bg.softuni.website.services.TreatmentService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
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
        return "treatments-page";
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
                            HttpServletResponse response) throws IOException {
        
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("newTreatmentDto", newTreatmentDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.newTreatmentDto", bindingResult);
            
            return "redirect:/treatments";
        }
        
        this.treatmentService.addNewTreatment(newTreatmentDto);
        
        return "redirect:/treatments?success=true";
    }
    
    @GetMapping("/treatments/{id}")
    public String deleteTreatment(@PathVariable Long id, Model model) throws IOException {
        boolean isDeleted = this.treatmentService.deleteTreatment(id);
        
        if (isDeleted) {
            model.addAttribute("success", "true");
            return "redirect:/treatments?success=true";
        }else {
            return "redirect:/treatments";
        }


//        if (isDeleted) {
//            return ResponseEntity.noContent().build();
//        } else {
//            return ResponseEntity.notFound().build();
//        }
    }
    
 
}
