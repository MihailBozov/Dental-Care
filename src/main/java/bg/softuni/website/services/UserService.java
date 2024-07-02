package bg.softuni.website.services;

import bg.softuni.website.models.dtos.RegisterDto;
import bg.softuni.website.models.entities.Role;
import bg.softuni.website.models.entities.UserEntity;
import bg.softuni.website.models.enums.UserRole;
import bg.softuni.website.repositories.RoleRepository;
import bg.softuni.website.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;
    
    
    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }
    
    public void registerUser(RegisterDto registerDto) {
        UserEntity userEntity = this.modelMapper.map(registerDto, UserEntity.class);
        userEntity.setPassword(this.passwordEncoder.encode(registerDto.getPassword()));
        
        Role role = this.roleRepository.findByName(UserRole.USER);
        userEntity.getRoles().add(role);
        this.userRepository.saveAndFlush(userEntity);
    }
    
}

