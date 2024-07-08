package bg.softuni.website.util.validation;

import bg.softuni.website.util.validation.validators.EditUniqueTreatmentImageNameValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = EditUniqueTreatmentImageNameValidator.class)
public @interface EditUniqueTreatmentImageName {
    
    String message() default "";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

