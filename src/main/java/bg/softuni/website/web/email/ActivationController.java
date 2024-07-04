package bg.softuni.website.web.email;

import bg.softuni.website.models.entities.UserEntity;
import bg.softuni.website.repositories.ActivationTokenRepository;
import bg.softuni.website.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

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
        
        Optional<UserEntity> matchUserWithToken = this.userService.matchToken(token);
        
        if (matchUserWithToken.isPresent()) {
            return "redirect:/?emailConfirmed=true&name=" + matchUserWithToken.get().getFirstName();
        }
        
        return "index";
    }
}
