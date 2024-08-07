package bg.softuni.website.web;

import bg.softuni.website.models.dtos.EditTreatmentDto;
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
        return this.treatmentService.getAllTreatmentDtos();
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

        this.treatmentService.newTreatment(newTreatmentDto);

        return "redirect:/treatments?success=true";
    }

    @DeleteMapping("/treatments/{id}")
    public String deleteTreatment(@PathVariable Long id) throws IOException {

        try {
            boolean isDeleted = this.treatmentService.deleteTreatment(id);
            if (isDeleted) {
                return "redirect:/treatments?success=true";
            }
            else {
                return "redirect:/treatments?success=false";
            }
        } catch (Exception e) {
            return "redirect:/treatments?success=false";
        }


    }

    @ModelAttribute("editTreatmentDto")
    public EditTreatmentDto initEditTreatmentDto() {
        return new EditTreatmentDto();
    }

    @GetMapping("treatments/{id}")
    @ResponseBody
    public EditTreatmentDto editTreatment(@PathVariable Long id) {
        return this.treatmentService.getEditTreatmentDto(id);
    }

    @PutMapping("treatments/{id}")
    public String editTreatment(@PathVariable Long id,
                                @Valid EditTreatmentDto editTreatmentDto,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) throws IOException {


        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("editTreatmentDto", editTreatmentDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.editTreatmentDto", bindingResult);

            return "redirect:/treatments";
        }


        boolean isEdited = this.treatmentService.editTreatment(editTreatmentDto, id);
        if (isEdited) {
            return "redirect:/treatments?success=true";
        }
        return "redirect:/treatments?success=false";


    }
}
