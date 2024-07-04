package bg.softuni.website.services;

import bg.softuni.website.models.dtos.RegisterDto;
import bg.softuni.website.models.entities.ActivationToken;
import bg.softuni.website.models.entities.Role;
import bg.softuni.website.models.entities.UserEntity;
import bg.softuni.website.models.enums.UserRole;
import bg.softuni.website.models.events.UserActivationUpponRegistrationEvent;
import bg.softuni.website.repositories.ActivationTokenRepository;
import bg.softuni.website.repositories.RoleRepository;
import bg.softuni.website.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private ActivationTokenRepository activationTokenRepository;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;
    private ApplicationEventPublisher applicationEventPublisher;
    
    
    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, ActivationTokenRepository activationTokenRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, ApplicationEventPublisher applicationEventPublisher) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.activationTokenRepository = activationTokenRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.applicationEventPublisher = applicationEventPublisher;
    }
    
    public void registerUser(RegisterDto registerDto) {
        UserEntity userEntity = this.modelMapper.map(registerDto, UserEntity.class);
        userEntity.setPassword(this.passwordEncoder.encode(registerDto.getPassword()));
        
        Role role = this.roleRepository.findByName(UserRole.USER);
        userEntity.getRoles().add(role);
        this.userRepository.saveAndFlush(userEntity);
        
        String token = UUID.randomUUID().toString();
        ActivationToken activationToken = new ActivationToken();
        activationToken.setToken(token);
        activationToken.setUser(userEntity);
        activationToken.setExpiryDate(LocalDateTime.now().plusHours(24));
        this.activationTokenRepository.saveAndFlush(activationToken);
        
        this.applicationEventPublisher.publishEvent(new UserActivationUpponRegistrationEvent("User Service", registerDto, token));
    }
    
    public Optional<UserEntity> matchToken(String token) {
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
            return null;
        }
        
    }
    
}

