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
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private ActivationTokenRepository activationTokenRepository;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;
    private ApplicationEventPublisher applicationEventPublisher;
    private EmailService emailService;
    private ActivationTokenService activationTokenService;
    
    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, ActivationTokenRepository activationTokenRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, ApplicationEventPublisher applicationEventPublisher, EmailService emailService, ActivationTokenService activationTokenService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.activationTokenRepository = activationTokenRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.applicationEventPublisher = applicationEventPublisher;
        this.emailService = emailService;
        this.activationTokenService = activationTokenService;
    }
    
    public void registerUser(RegisterDto registerDto) {
        UserEntity userEntity = this.modelMapper.map(registerDto, UserEntity.class);
        userEntity.setPassword(this.passwordEncoder.encode(registerDto.getPassword()));
        
        Role role = this.roleRepository.findByName(UserRole.USER);
        userEntity.getRoles().add(role);
        this.userRepository.saveAndFlush(userEntity);
        
        ActivationToken activationToken = this.activationTokenService.createActivationToken(userEntity);
        this.applicationEventPublisher.publishEvent(new UserActivationUpponRegistrationEvent("User Service", registerDto, activationToken.getToken()));
    }
    
    public Optional<UserEntity> matchTokensActivateUser(String token) {
        try {
            ActivationToken activationToken = activationTokenRepository
                    .findByToken(token)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid activation token"));
            
            UserEntity user = activationToken.getUser();
            user.setActive(true);
            userRepository.saveAndFlush(user);
            activationTokenRepository.delete(activationToken);
            return Optional.of(user);
       
        } catch (Exception e) {
            return Optional.empty();
        }
        
    }
    
    public boolean doPasswordReset(String email) {
        try {
            UserEntity user = userRepository.findByEmail(email).orElse(null);
            if (user == null) {
                return false;
            }
            
            ActivationToken activationToken = this.activationTokenService.createActivationToken(user);
            this.emailService.sendResetPasswordEmail(user, activationToken.getToken());
            return true;
            
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean matchToken(String token) {
        try {
            ActivationToken activationToken = this.activationTokenRepository
                    .findByToken(token)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid activation token"));
            
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
    
    @Transactional
    public boolean updateUserPassword(ResetPasswordDto resetPasswordDto) {
        
        try {
            ActivationToken activationToken = this.activationTokenRepository
                    .findByToken(resetPasswordDto.getToken())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid activation token"));
            
            UserEntity user = this.userRepository
                    .findByEmail(activationToken.getUser().getEmail())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid user"));
            
            user.setPassword(this.passwordEncoder.encode(resetPasswordDto.getPassword()));
            this.userRepository.saveAndFlush(user);
            return true;
        } catch (Exception e) {
            return false;
        }
        
    }
}

