package bg.softuni.website.web.email;

import bg.softuni.website.models.dtos.ResetPasswordDto;
import bg.softuni.website.models.entities.ActivationToken;
import bg.softuni.website.models.entities.UserEntity;
import bg.softuni.website.repositories.ActivationTokenRepository;
import bg.softuni.website.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ActivationControllerTest {
    
    @MockBean
    private ActivationTokenRepository activationTokenRepository;
    
    @MockBean
    private UserService userService;
    
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private WebApplicationContext context;
    
    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
    
    @Test
    void activateAccount() throws Exception {
        UserEntity user = new UserEntity();
        user.setFirstName("SandTest");
        when(userService.matchTokensActivateUser(anyString())).thenReturn(Optional.of(user));
        
        mockMvc.perform(MockMvcRequestBuilders.get("/activate-account/token"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/?emailConfirmed=true&name=SandTest"));
        
        verify(userService, times(1))
                .matchTokensActivateUser("token");
    }
    
    @Test
    void activateAccountWithInvalidUser() throws Exception {
        when(userService.matchTokensActivateUser(anyString()))
                .thenReturn(Optional.empty());
        mockMvc.perform(MockMvcRequestBuilders.get("/activate-account/token"))
                .andExpect(status().isOk())
                .andExpect(view().name("error/404"));
        
    }
    
    @Test
    void resetPassword() throws Exception {
        when(userService.matchToken(anyString()))
                .thenReturn(true);
        
        mockMvc.perform(MockMvcRequestBuilders.get("/reset-password/token"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/?reset-password=true&token=token"));
    }
    
    @Test
    void resetPasswordWithInvalidToken() throws Exception {
        when(userService.matchToken(anyString()))
                .thenReturn(false);
        
        mockMvc.perform(MockMvcRequestBuilders.get("/reset-password/token"))
                .andExpect(status().isOk())
                .andExpect(view().name("error/404"));
    }
    
    @Test
    void resetPasswordDto() throws Exception {
        ResetPasswordDto resetPasswordDto = new ResetPasswordDto();
        resetPasswordDto.setToken("token");
        resetPasswordDto.setPassword("passwordTest");
        resetPasswordDto.setConfirmPassword("passwordTest");
        
        UserEntity user = new UserEntity();
        user.setFirstName("SandTest");
        
        ActivationToken activationToken  = new ActivationToken();
        activationToken.setToken("token");
        activationToken.setUser(user);
        Optional<ActivationToken> activationTokenOptional = Optional.of(activationToken);
        
        
        when(userService.updateUserPassword(any(ResetPasswordDto.class))).thenReturn(true);
        when(activationTokenRepository.findByToken(anyString())).thenReturn(activationTokenOptional);
        
        mockMvc.perform(MockMvcRequestBuilders.post("/reset-password")
                        .flashAttr("resetPasswordDto", resetPasswordDto))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/?resetPasswordDone=true&name=SandTest"));
        
    }
    
    @Test
    void resetPasswordDtoWithInvalidToken() throws Exception {
        ResetPasswordDto resetPasswordDto = new ResetPasswordDto();
        resetPasswordDto.setToken("");
        resetPasswordDto.setPassword("passwordTest");
        resetPasswordDto.setConfirmPassword("passwordTest");
        
        when(userService.updateUserPassword(any(ResetPasswordDto.class))).thenReturn(false);
        when(activationTokenRepository.findByToken(anyString())).thenReturn(Optional.of(new ActivationToken()));
        
        mockMvc.perform(MockMvcRequestBuilders.post("/reset-password")
                        .flashAttr("resetPasswordDto", resetPasswordDto))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/?success=false"));
        
    }
    
    @Test
    void resetPasswordDtoThrowException() throws Exception {
        
        ResetPasswordDto resetPasswordDto = new ResetPasswordDto();
        resetPasswordDto.setToken("token");
        resetPasswordDto.setPassword("passwordTest");
        resetPasswordDto.setConfirmPassword("passwordTest");
        
        when(userService.updateUserPassword(any(ResetPasswordDto.class))).thenThrow(new RuntimeException());
        
        mockMvc.perform(MockMvcRequestBuilders.post("/reset-password")
                        .flashAttr("resetPasswordDto", resetPasswordDto))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/?success=false"));
        
    }
    
    
    @Test
    void resetPasswordDtoWithValidationError() throws Exception {
        ResetPasswordDto resetPasswordDto = new ResetPasswordDto();
        resetPasswordDto.setToken("token");
        resetPasswordDto.setPassword("");
        resetPasswordDto.setConfirmPassword("");
        
        mockMvc.perform(MockMvcRequestBuilders.post("/reset-password")
                .flashAttr("resetPasswordDto", resetPasswordDto))
                .andExpect(flash().attributeExists("resetPasswordDto"))
                .andExpect(flash().attributeExists("org.springframework.validation.BindingResult.resetPasswordDto"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
        
        
    }
}