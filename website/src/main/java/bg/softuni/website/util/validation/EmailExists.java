package bg.softuni.website.util.validation;

import bg.softuni.website.util.validation.validators.EmailExistsValidator;
import bg.softuni.website.util.validation.validators.UniqueRegisterEmailValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailExistsValidator.class)
public @interface EmailExists {
    
    String message() default "";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    
}
