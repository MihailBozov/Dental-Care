package bg.softuni.website.web;

import bg.softuni.website.models.dtos.RegisterDto;
import bg.softuni.website.services.UserService;
import bg.softuni.website.services.NewsletterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
public class AuthController {
    
    private UserService userService;
    private NewsletterService newsletterService;
    
    @Autowired
    public AuthController(UserService userService, NewsletterService newsletterService) {
        this.userService = userService;
        this.newsletterService = newsletterService;
    }


//    -------------------     register     -------------------
    
    
    @ModelAttribute("registerDto")
    public RegisterDto initRegisterDto() {
        return new RegisterDto();
    }
    
    @GetMapping("/register")
    public String register() {
        return "register-page";
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
        
        this.userService.registerUser(registerDto);
        
        return "redirect:/login";
    }


//    -------------------     login     -------------------
    
    
    @GetMapping("/login")
    public String login() {
        return "login-page";
    }
    
    @PostMapping("/logout")
    public String logout() {
        return "redirect:/index";
    }
    
    @PostMapping("/login-error")
    public String onFailedLogin(@ModelAttribute("email") String email, Model model) {
        model.addAttribute("email", email);
        model.addAttribute("bad_credentials", "true");
        return "login-page";
    }
}