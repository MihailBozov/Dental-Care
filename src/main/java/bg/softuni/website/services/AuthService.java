package bg.softuni.website.services;

import bg.softuni.website.models.dtos.LoginDto;
import bg.softuni.website.models.dtos.RegisterDto;
import bg.softuni.website.models.entities.Role;
import bg.softuni.website.models.entities.User;
import bg.softuni.website.models.enums.UserRole;
import bg.softuni.website.repositories.RoleRepository;
import bg.softuni.website.repositories.UserRepository;
import bg.softuni.website.sessions.UserSession;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;
    private UserSession userSession;
    
    @Autowired
    public AuthService(UserRepository userRepository, RoleRepository roleRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, UserSession userSession) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.userSession = userSession;
    }
    
    public void registerUser(RegisterDto registerDto) {
        User user = this.modelMapper.map(registerDto, User.class);
        user.setPassword(this.passwordEncoder.encode(registerDto.getPassword()));
        
        Role role = this.roleRepository.findByName(UserRole.USER);
        user.getRoles().add(role);
        this.userRepository.saveAndFlush(user);
    }
    
    public void loginUser(LoginDto loginDto) {
        Optional<User> user = this.userRepository.findByEmail(loginDto.getEmail());
        this.userSession.login(user.get());
    }
    
    public void logoutUser() {
        this.userSession.logout();
    }
    
    public boolean isLoggedIn() {
        
        return this.userSession.getId() != 0;
    }
}

