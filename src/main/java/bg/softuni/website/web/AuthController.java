package bg.softuni.website.web;

import bg.softuni.website.models.dtos.LoginDto;
import bg.softuni.website.models.dtos.RegisterDto;
import bg.softuni.website.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/")
public class AuthController {
    
    private AuthService authService;
    
    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    
    @ModelAttribute
    public RegisterDto initRegisterDto() {
        return new RegisterDto();
    }
    
    @GetMapping("/register")
    public String register() {
        return "registerPage";
    }
    
    @PostMapping("/register")
    public String doRegister(@Valid RegisterDto registerDto,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("registerDto", registerDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registerDto", bindingResult);
            return "redirect:/register";
        }
        
        this.authService.registerUser(registerDto);
        
        return "redirect:/login";
    }
    
    
//    --------    login    --------
    
    @GetMapping("/login")
    public String login() {
        return "loginPage";
    }
    
    @ModelAttribute
    public LoginDto initLoginDto() {
        return new LoginDto();
    }
    
    
    @PostMapping("/login") 
    public String login(@Valid LoginDto loginDto,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("loginDto", loginDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.loginDto", bindingResult);

            return "redirect:/login";
        }

        return "redirect:/";
    }
        
        
}













