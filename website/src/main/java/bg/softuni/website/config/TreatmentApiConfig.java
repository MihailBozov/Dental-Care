package bg.softuni.website.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "treatment.api")
public class TreatmentApiConfig {
    
    private String baseUrl;
    
}
