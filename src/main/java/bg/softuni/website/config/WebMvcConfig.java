package bg.softuni.website.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

//    @Value("${image.upload.dir}")
//    private String uploadDir;

    private final Environment environment;

    @Autowired
    public WebMvcConfig(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String uploadDir1 = environment.getProperty("image.upload.formNewTreatmentDir");
//        String uploadDir2 = environment.getProperty("image.upload.dir2");

        registry.addResourceHandler("/images/treatments/**")
                .addResourceLocations("file:" + uploadDir1 + "/");

//        registry.addResourceHandler("/images/treatments/**")
//                .addResourceLocations("file:" + uploadDir2);
    }

}
