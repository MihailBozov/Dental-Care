package bg.softuni.website.services;

import bg.softuni.website.models.entities.Role;
import bg.softuni.website.models.entities.UserEntity;
import bg.softuni.website.models.enums.UserRole;
import bg.softuni.website.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MyUserDetailsServiceTest {
    
    private MyUserDetailsService myUserDetailsServiceTest;
    
    @Mock
    private UserRepository mockUserRepository;
    
    @BeforeEach
    void setUp() {
        myUserDetailsServiceTest = new MyUserDetailsService(mockUserRepository);
    }
    
    @Test
    void testUserNotFound() {
        Assertions.assertThrows(UsernameNotFoundException.class,
                () -> myUserDetailsServiceTest.loadUserByUsername("sand@gmail.com"));
        
    }
    
    @Test
    void UserFoundTest() {
//        Arrange
        UserEntity testUserEntity = createUserEntity();
        when(mockUserRepository.findByEmail(testUserEntity.getEmail()))
                .thenReturn(Optional.of(testUserEntity));
        
//        Act
        UserDetails testUserDetails =  myUserDetailsServiceTest.loadUserByUsername(testUserEntity.getEmail());
        
//        Assert
        Assertions.assertNotNull(testUserDetails);
        Assertions.assertEquals("yanica@gmail.com", testUserDetails.getUsername(), "WRONG USERNAME");
        Assertions.assertEquals(testUserDetails.getUsername(), testUserEntity.getEmail(), "THE USERNAME OF testUserEntity AND testUserDetails ARE DIFFERENT");
        Assertions.assertEquals(testUserDetails.getPassword(), testUserEntity.getPassword(), "THE PASSWORD OF testUserEntity AND testUserDetails ARE DIFFERENT");
        Assertions.assertEquals(2, testUserDetails.getAuthorities().size());
        Assertions.assertTrue(containsAuthority(testUserDetails, "ROLE_" + UserRole.ADMIN), "THE ROLES DO NOT MATCH");
        Assertions.assertTrue(testUserEntity.isActive());
    }  
    
    @Test
    void userEntityIsActiveFalseTest() {
        UserEntity testUserEntity = createUserEntity();
        testUserEntity.setActive(false);
        
        when(mockUserRepository.findByEmail(testUserEntity.getEmail()))
                .thenReturn(Optional.of(testUserEntity));
        
                
        Assertions.assertThrows(UsernameNotFoundException.class,
                () -> myUserDetailsServiceTest.loadUserByUsername(testUserEntity.getEmail()));
    }
    
    
    
    private boolean containsAuthority(UserDetails userDetails, String expectedAuthority) {
        return userDetails
                .getAuthorities()
                .stream()
                .anyMatch(authority -> authority.getAuthority().equals(expectedAuthority));
    }
    
    private static UserEntity createUserEntity() {
        UserEntity user = new UserEntity();
        user.setFirstName("Yanica");
        user.setLastName("Petrova");
        user.setEmail("yanica@gmail.com");
        user.setPassword("1212");
        user.setActive(true);
        user.setRoles(List.of(new Role(UserRole.ADMIN), new Role(UserRole.USER)));
        return user;
    }
    
}
