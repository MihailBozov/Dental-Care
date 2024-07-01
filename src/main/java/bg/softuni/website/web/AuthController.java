package bg.softuni.website.web;

import bg.softuni.website.models.dtos.LoginDto;
import bg.softuni.website.models.dtos.RegisterDto;
import bg.softuni.website.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
public class AuthController {
    
    private AuthService authService;
    
    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    
    @ModelAttribute("registerDto")
    public RegisterDto initRegisterDto() {
        return new RegisterDto();
    }
    
    @GetMapping("/register")
    public String register() {
        
        if (this.authService.isLoggedIn()) {
            return "redirect:/";
        }
        
        return "registerPage";
    }
    
    @PostMapping("/register")
    public String doRegister(@Valid RegisterDto registerDto,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        
        if (this.authService.isLoggedIn()) {
            return "redirect:/";
        }
        
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
        
        if (this.authService.isLoggedIn()) {
            return "redirect:/";
        }
        
        return "loginPage";
    }
    
    @ModelAttribute("loginDto")
    public LoginDto initLoginDto() {
        return new LoginDto();
    }
    
    
    @PostMapping("/login") 
    public String login(@Valid LoginDto loginDto,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes) {
        
        if (this.authService.isLoggedIn()) {
            return "redirect:/";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("loginDto", loginDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.loginDto", bindingResult);
            
            return "redirect:/login";
        }
        
        this.authService.loginUser(loginDto);

        return "redirect:/";
    }
    
    @GetMapping("/logout")
    public String logout() {
        
        if (!this.authService.isLoggedIn()) {
            return "redirect:/";
        }
        
        this.authService.logoutUser();
        return "index";
    }
}













