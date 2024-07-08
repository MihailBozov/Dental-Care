package bg.softuni.website.web;

import bg.softuni.website.models.dtos.ForgotPasswordDto;
import bg.softuni.website.models.dtos.RegisterDto;
import bg.softuni.website.services.NewsletterService;
import bg.softuni.website.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private UserService userService;
    
    @MockBean
    NewsletterService newsletterService;
    
    @Autowired
    private WebApplicationContext context;
    
    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
    
    @Test
    void registerPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("register-page"))
                .andExpect(model().attributeExists("registerDto"));
    }
    
    @Test
    void doRegister() throws Exception {
        RegisterDto registerDto = new RegisterDto();
        registerDto.setEmail("mihailbozov@yahoo.com");
        registerDto.setFirstName("SandTest");
        registerDto.setLastName("PetrovTest");
        registerDto.setPassword("passwordTest");
        registerDto.setConfirmPassword("passwordTest");
        
        mockMvc.perform(MockMvcRequestBuilders.post("/register")
                        .flashAttr("registerDto", registerDto))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/?confirmationSent=true&name=SandTest&email=mihailbozov@yahoo.com"));
        
        verify(userService, times(1))
                .registerUser(any(RegisterDto.class));
    }
    
    @Test
    void doRegisterWithErrors() throws Exception {
        RegisterDto registerDto = new RegisterDto();
        registerDto.setEmail("mihailbozov@yahoo.com");
        registerDto.setFirstName("");
        registerDto.setLastName("Petrov");
        registerDto.setPassword("passwordTest");
        registerDto.setConfirmPassword("passwordTest");
        
        mockMvc.perform(MockMvcRequestBuilders.post("/register")
                        .flashAttr("registerDto", registerDto))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/register"))
                .andExpect(flash().attributeExists("registerDto"))
                .andExpect(flash().attributeExists("org.springframework.validation.BindingResult.registerDto"));
        verify(userService, never())
                .registerUser(any(RegisterDto.class));
    }
    
    @Test
    void login() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/login")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("login-page"));
        
    }
    
    @Test
    void forgotPassword() throws Exception {
        ForgotPasswordDto forgotPasswordDto = new ForgotPasswordDto();
        forgotPasswordDto.setEmail("mihailbozov@hotmail.com");
        
        
        mockMvc.perform(MockMvcRequestBuilders.post("/login/forgot-password")
                        .flashAttr("forgotPasswordDto", forgotPasswordDto))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login?resetPasswordEmailSent=true"));
        
        verify(userService, times(1))
                .doPasswordReset(forgotPasswordDto.getEmail());
    }
    
    @Test
    void forgotPasswordWithErrors() throws Exception {
        ForgotPasswordDto forgotPasswordDto = new ForgotPasswordDto();
        forgotPasswordDto.setEmail("");
        
        mockMvc.perform(MockMvcRequestBuilders.post("/login/forgot-password")
                        .flashAttr("forgotPasswordDto", forgotPasswordDto)
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"))
                .andExpect(flash().attributeExists("forgotPasswordDto"))
                .andExpect(flash().attributeExists("org.springframework.validation.BindingResult.forgotPasswordDto"));
        
        verify(userService, never())
                .doPasswordReset(anyString());
    }
    
    @Test
    @WithMockUser
    void logout() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/logout"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/index"));
        
    }
    
    @Test
    void onFailedLogin() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/login-error")
                        .param("email", "test@gmail.com")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("login-page"))
                .andExpect(model().attribute("email", "test@gmail.com"))
                .andExpect(model().attribute("bad_credentials", "true"));
        
    }
}