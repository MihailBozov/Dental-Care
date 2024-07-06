package bg.softuni.website.web.email;

import bg.softuni.website.models.dtos.ResetPasswordDto;
import bg.softuni.website.models.entities.UserEntity;
import bg.softuni.website.repositories.ActivationTokenRepository;
import bg.softuni.website.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class ActivationController {
    
    private ActivationTokenRepository activationTokenRepository;
    private UserService userService;
    
    @Autowired
    public ActivationController(ActivationTokenRepository activationTokenRepository, UserService userService) {
        this.activationTokenRepository = activationTokenRepository;
        this.userService = userService;
    }
    
    @GetMapping("/activate-account/{token}")
    public String activateAccount(@PathVariable("token") String token) {
        Optional<UserEntity> matchUserWithToken = this.userService.matchUserWithToken(token);
        
        if (matchUserWithToken.isPresent()) {
            return "redirect:/?emailConfirmed=true&name=" + matchUserWithToken.get().getFirstName();
        }
        return "error/404";
    }
    
    
    @GetMapping("/reset-password/{token}")
    public String resetPassword(@PathVariable String token) {
        if (this.userService.match(token)) {
            return "redirect:/?reset-password=true&token=" + token;
        }
        return "error/404";
    }
    
    
    @ModelAttribute("resetPasswordDto")
    public ResetPasswordDto resetPasswordDto() {
        return new ResetPasswordDto();
    }
    
    @PostMapping("/reset-password")
    public String resetPassword(@Valid ResetPasswordDto resetPasswordDto,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {
        
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("resetPasswordDto", resetPasswordDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.resetPasswordDto", bindingResult);
            return "redirect:/";
        }
        
        try {
            boolean isValid = this.userService.updateUserPassword(resetPasswordDto);
            if (isValid) {
                return "redirect:/?resetPasswordDone=true&name=" + activationTokenRepository
                        .findByToken(resetPasswordDto.getToken())
                        .get()
                        .getUser()
                        .getFirstName();
            }
            return "redirect:/?success=false";
            
        } catch (Exception e) {
            return "redirect:/?success=false";
        }
    }
}








