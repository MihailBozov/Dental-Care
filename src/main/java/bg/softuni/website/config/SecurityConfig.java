package bg.softuni.website.config;

import bg.softuni.website.models.enums.UserRole;
import bg.softuni.website.repositories.UserRepository;
import bg.softuni.website.services.MyUserDetailsService;
import bg.softuni.website.util.handlers.CustomAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Value("${remember.me.key}")
    String rememberMeKey;
    
    private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    
    @Autowired
    public SecurityConfig(CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler) {
        this.customAuthenticationSuccessHandler = customAuthenticationSuccessHandler;
    }
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                
                //  define which urls are visible by which users
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        
                        //  all static resources which are situated in js, images, css are available for anyone
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        
                        //  allow anyone to see the homepage, register page and logout page
                        .requestMatchers("/", "/login**", "/register", "/logout", "/treatments", "/login-error").permitAll()
                        
                        .requestMatchers("/reset-password/**").permitAll()
                        .requestMatchers("login/forgot-password").permitAll()
                        
                        //  allow who can see what
                        .requestMatchers("/users/**").hasAnyRole(UserRole.ADMIN.name(), UserRole.MANAGER.name(), UserRole.DENTIST.name(), UserRole.DENTAL_ASSISTANT.name())
                        .requestMatchers("/doctors/**").hasAnyRole(UserRole.ADMIN.name(), UserRole.MANAGER.name())
                        .requestMatchers("/managers/**").hasRole(UserRole.ADMIN.name())
                        .requestMatchers("/error/**").permitAll()
                        .requestMatchers("/newsletter").permitAll()
                        .requestMatchers("/treatments/newTreatment/**").hasAnyRole(UserRole.ADMIN.name(), UserRole.MANAGER.name(), UserRole.DENTIST.name(), UserRole.DENTAL_ASSISTANT.name())
                        
                        
                        //  who can use which http methods
                        .requestMatchers(HttpMethod.GET).permitAll()
                        .requestMatchers(HttpMethod.POST).hasAnyRole(UserRole.ADMIN.name(), UserRole.MANAGER.name(), UserRole.DENTIST.name(), UserRole.DENTAL_ASSISTANT.name())
                        .requestMatchers(HttpMethod.DELETE).hasAnyRole(UserRole.ADMIN.name(), UserRole.MANAGER.name(), UserRole.DENTIST.name(), UserRole.DENTAL_ASSISTANT.name())
                        .requestMatchers(HttpMethod.PUT).hasAnyRole(UserRole.ADMIN.name(), UserRole.MANAGER.name(), UserRole.DENTIST.name(), UserRole.DENTAL_ASSISTANT.name())
                        .requestMatchers(HttpMethod.PATCH).hasAnyRole(UserRole.ADMIN.name(), UserRole.MANAGER.name(), UserRole.DENTIST.name(), UserRole.DENTAL_ASSISTANT.name())
                        .requestMatchers(HttpMethod.OPTIONS).hasRole(UserRole.ADMIN.name())
                        .requestMatchers(HttpMethod.TRACE).hasRole(UserRole.ADMIN.name())
                        .requestMatchers(HttpMethod.HEAD).hasRole(UserRole.ADMIN.name())
                        //  .requestMatchers(HttpMethod.GET, "/api/**").hasAnyRole("USER", "ADMIN")
                        
                        //  all other requests are authenticated
                        .anyRequest().authenticated()
                )
                .formLogin(formLogin -> formLogin
                        //  redirect here when we access something that is not allowed
                        //  also this is the page where we perform login
                        .loginPage("/login")
                        //  the names of the input fields (in our case in login.html
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/", true)
                        .successHandler(customAuthenticationSuccessHandler)
                        .failureForwardUrl("/login-error")
                
                )
                .logout(logout -> logout
                        //  the url where we should POST something in order to perform the logout
                        .logoutUrl("/logout")
                        //  where to go on logout
                        .logoutSuccessUrl("/")
                        //  invalidate the http session
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                
                )
                .csrf(csrf -> csrf
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                )
                .rememberMe(rememberMe -> {
                            rememberMe
                                    .key(rememberMeKey)
                                    .rememberMeParameter("remember-me")
                                    .rememberMeCookieName("remember-me");
                        }
                ).build();
        
        // TODO: remember me!
        
        
    }
    
    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        //  this service translates the website's users and roles
        //  to representation which spring security understands 
        return new MyUserDetailsService(userRepository);
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8();
    }
    
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        configuration.addAllowedOrigin("http://localhost:5173");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", configuration);
        return new CorsFilter(source);
    }


//    @Bean
//    public PasswordEncoder passwordEncoder() {
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

