package bg.softuni.website.services;

import bg.softuni.website.models.entities.UserEntity;
import bg.softuni.website.models.enums.UserRole;
import bg.softuni.website.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class MyUserDetailsService implements UserDetailsService {
    
    private UserRepository userRepository;
    
    public MyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository
                .findByEmail(email)
                .map(this::map)
                .orElseThrow(() -> new UsernameNotFoundException("User with " + email + " not found"));
    }
    
    
    private UserDetails map(UserEntity userEntity) {
        
        Set<GrantedAuthority> roles = userEntity
                .getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName().name()))
                .collect(Collectors.toSet());
        
        if (!userEntity.isActive()) {
            throw new UsernameNotFoundException("User is not active");
        }
        
        return User
                .withUsername(userEntity.getEmail())
                .password(userEntity.getPassword())
                .authorities(roles)
                .build();
    }
}
