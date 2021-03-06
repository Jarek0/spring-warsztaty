package pl.edu.pollub.warsztaty.common.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(ElementType.TYPE)
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = StartsWithSameLetterValidator.class)
public @interface StartsWithSameLetter {
    String message();
    String name();
    String category();

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
