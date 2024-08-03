package bg.softuni.website.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;


@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

//    @Value("${image.upload.dir}")
//    private String uploadDir;

    private final Environment environment;
    private final LocaleChangeInterceptor localeChangeInterceptor;

    @Autowired
    public WebMvcConfig(Environment environment, LocaleChangeInterceptor localeChangeInterceptor) {
        this.environment = environment;
        this.localeChangeInterceptor = localeChangeInterceptor;
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
    
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor);
    }

}
