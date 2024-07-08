package bg.softuni.website.util.handlers;

import bg.softuni.website.models.entities.UserEntity;
import bg.softuni.website.repositories.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private final Logger logger = LoggerFactory.getLogger(CustomAuthenticationSuccessHandler.class);
    private final UserRepository userRepository;
    
    @Autowired
    public CustomAuthenticationSuccessHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String username = ((User) authentication.getPrincipal()).getUsername();
        Optional<UserEntity> userOpt = userRepository.findByEmail(username);
        if (userOpt.isPresent()) {
            userOpt.get().setLastLogin(LocalDateTime.now());
            logger.info("User {} logged in", username);
            userRepository.save(userOpt.get());
            response.sendRedirect("/");
        }
        else {
            logger.error("The user {} is not logged in", username);
        }
    }
}
