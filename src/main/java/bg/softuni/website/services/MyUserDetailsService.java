package bg.softuni.website.services;

import bg.softuni.website.models.entities.UserEntity;
import bg.softuni.website.repositories.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

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
        return User
                .withUsername(userEntity.getFirstName())
                .password(userEntity.getPassword())
                .authorities(List.of()) //TODO   add roles
                .build();
    }
}
