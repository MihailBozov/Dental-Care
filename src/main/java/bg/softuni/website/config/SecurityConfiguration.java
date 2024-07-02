package bg.softuni.website.config;

import bg.softuni.website.repositories.UserRepository;
import bg.softuni.website.services.MyUserDetailsService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                        //  define which urls are visible by which users
                        authorizeRequests -> authorizeRequests
                                //  all static resources which are situated in js, images, css are available for anyone
                                .requestMatchers(PathRequest
                                        .toStaticResources()
                                        .atCommonLocations()).permitAll()
                                //  allow anyone to see the homepage, register page and logout page
                                .requestMatchers(
                                        "/",
                                        "/users/login",
                                        "/users/register",
                                        "/users/logout").permitAll()
                                //  all other requests are authenticated
                                .anyRequest().authenticated())
                .formLogin(
                        fotmLogin -> {
                            fotmLogin.
                                    //  redirect here when we access something that is not allowed
                                    //  also this is the page where we perform login
                                            loginPage("users/login")
                                    //  the names of the input fields (in our case in login.html
                                    .usernameParameter("email")
                                    .passwordParameter("password")
                                    .defaultSuccessUrl("/")
                                    .failureForwardUrl("users/login-error");
                        }
                ).logout( logout -> {
                    logout
                            //  the url where we should POST something in order to perform the logout
                            .logoutUrl("/logout")
                            //  where to go on logout
                            .logoutSuccessUrl("/")
                            //  invalidate the http session
                            .invalidateHttpSession(true);
                        }
                );
        
        // TODO: remember me!
        
        return http.build();
    }
    
    @Bean
    UserDetailsService userDetailsService(UserRepository userRepository) {
        //  this service translates the website's users and roles
        //  to representation which spring security understands 
        return new MyUserDetailsService(userRepository);
    }
    
    @Bean
    PasswordEncoder passwordEncoder() {
        return Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8();
    }
    
    
    
//    @Bean
//    PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.csrf(csrf -> csrf.disable()) // Disable CSRF protection
//                .authorizeHttpRequests(authorize -> authorize
//                        .anyRequest().permitAll()); // Allow all requests
//        return http.build();
//    }
    
}

