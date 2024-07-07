package bg.softuni.website.services;

import bg.softuni.website.models.dtos.RegisterDto;
import bg.softuni.website.models.dtos.ResetPasswordDto;
import bg.softuni.website.models.entities.ActivationToken;
import bg.softuni.website.models.entities.Role;
import bg.softuni.website.models.entities.UserEntity;
import bg.softuni.website.models.enums.UserRole;
import bg.softuni.website.models.events.UserActivationUpponRegistrationEvent;
import bg.softuni.website.repositories.ActivationTokenRepository;
import bg.softuni.website.repositories.RoleRepository;
import bg.softuni.website.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    
    @Mock
    private UserRepository userRepository;
    
    @Mock
    private RoleRepository roleRepository;
    
    @Mock
    private ActivationTokenRepository activationTokenRepository;
    
    @Mock
    private ModelMapper modelMapper;
    
    @Mock
    private PasswordEncoder passwordEncoder;
    
    @Mock
    private ApplicationEventPublisher applicationEventPublisher;
    
    @Mock
    private EmailService emailService;
    
    @Mock
    private ActivationTokenService activationTokenService;
    
    @InjectMocks
    private UserService userService;
    
    
    private RegisterDto registerDto;
    private UserEntity userEntity;
    private Role role;
    private ActivationToken activationToken;
    
    @BeforeEach
    void setUp() {
        registerDto = new RegisterDto();
        registerDto.setFirstName("Sand");
        registerDto.setLastName("Petrov");
        registerDto.setEmail("sand@gmail.com");
        registerDto.setPassword("password");
        registerDto.setConfirmPassword("password");
        
        userEntity = new UserEntity();
        userEntity.setFirstName("Sand");
        userEntity.setLastName("Petrov");
        userEntity.setEmail("sand@gmail.com");
        userEntity.setPassword("encodedPassword");
        
        role = new Role();
        role.setName(UserRole.USER);
        
        activationToken = new ActivationToken();
        activationToken.setUser(userEntity);
        activationToken.setToken("token");
    }
    
    
    @Test
    void registerUserTest() {
        when(modelMapper.map(registerDto, UserEntity.class))
                .thenReturn(userEntity);
        when(passwordEncoder.encode(registerDto.getPassword()))
                .thenReturn("encodedPassword");
        when(roleRepository.findByName(UserRole.USER))
                .thenReturn(role);
        when(activationTokenService.createActivationToken(userEntity))
                .thenReturn(activationToken);
        
        userService.registerUser(registerDto);
        
        verify(userRepository, times(1))
                .saveAndFlush(userEntity);
        verify(applicationEventPublisher, times(1))
                .publishEvent(any(UserActivationUpponRegistrationEvent.class));
    }
    
    @Test
    void matchTokensActivateUserTest() {
        when(activationTokenRepository.findByToken("token"))
                .thenReturn(Optional.of(activationToken));
        
        Optional<UserEntity> user = userService.matchTokensActivateUser("token");
        Assertions.assertTrue(user.isPresent());
        Assertions.assertTrue(user.get().isActive());
        verify(userRepository, times(1))
                .saveAndFlush(userEntity);
        verify(activationTokenRepository, times(1))
                .delete(activationToken);
    }
    
    @Test
    void matchTokensActivateUserWithInvalidTokenTest() {
        when(activationTokenRepository.findByToken("token"))
                .thenReturn(Optional.empty());
        IllegalArgumentException exception =  Assertions.assertThrows(IllegalArgumentException.class, () -> {
            userService.matchTokensActivateUser("token");
        });
    }
    
    @Test
    void doPasswordResetTest() {
        when(userRepository.findByEmail("sand@gmail.com"))
                .thenReturn(Optional.ofNullable(userEntity));
        
        when(activationTokenService.createActivationToken(userEntity))
                .thenReturn(activationToken);
        
       boolean result = userService.doPasswordReset("sand@gmail.com");
       
       Assertions.assertTrue(result);
       verify(activationTokenService, times(1))
               .createActivationToken(any(UserEntity.class));
       
       verify(emailService, times(1))
               .sendResetPasswordEmail(any(UserEntity.class), any());
    }
    
    @Test 
    void doPasswordResetWithInvalidUserTest() {
        when(userRepository.findByEmail("sand@gmail.com"))
                .thenReturn(Optional.empty());
        boolean result = userService.doPasswordReset("sand@gmail.com");
        Assertions.assertFalse(result);
    }
    
    
    
    @Test
    void matchToken() {
        when(activationTokenRepository.findByToken("token"))
                .thenReturn(Optional.of(activationToken));
        boolean result = userService.matchToken("token");
        Assertions.assertTrue(result);
    }
    
    @Test
    void matchTokenInvalidWithInvalidTokenTest() {
        when(activationTokenRepository.findByToken("token"))
                .thenReturn(Optional.empty());
        
//        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
//            userService.matchToken("token");
//        });
        
        boolean result = userService.matchToken("token");
        Assertions.assertFalse(result);
    }
    
    @Test
    void updateUserPasswordTest() {
        ResetPasswordDto resetPasswordDto = new ResetPasswordDto();
        resetPasswordDto.setToken("token");
        resetPasswordDto.setPassword("newPassword");
        resetPasswordDto.setConfirmPassword("newPassword");
        
        when(activationTokenRepository.findByToken("token"))
                .thenReturn(Optional.of(activationToken));
        
        when(userRepository.findByEmail("sand@gmail.com"))
                .thenReturn(Optional.of(userEntity));
        
        when(passwordEncoder.encode("newPassword"))
                .thenReturn("encodedNewPassword");
        
        boolean result = userService.updateUserPassword(resetPasswordDto);
        
        Assertions.assertTrue(result);
        verify(userRepository, times(1))
                .saveAndFlush(userEntity);
    }
    
    @Test
    void updateUserPasswordWithInvalidTokenTest() {
        ResetPasswordDto resetPasswordDto = new ResetPasswordDto();
        resetPasswordDto.setToken("token");
        resetPasswordDto.setPassword("newPassword");
        
        when(activationTokenRepository.findByToken("token"))
                .thenReturn(Optional.empty());
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
           userService.updateUserPassword(resetPasswordDto); 
        });
    }
    
    @Test
    void updateUserPasswordWithInvalidUserTest() {
        ResetPasswordDto resetPasswordDto = new ResetPasswordDto();
        resetPasswordDto.setToken("token");
        resetPasswordDto.setPassword("newPassword");
        
        when(activationTokenRepository.findByToken("token"))
                .thenReturn(Optional.of(activationToken));
        when(userRepository.findByEmail(activationToken.getUser().getEmail()))
                .thenReturn(Optional.empty());
        
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
           boolean result = userService.updateUserPassword(resetPasswordDto); 
        });
        
    }
}