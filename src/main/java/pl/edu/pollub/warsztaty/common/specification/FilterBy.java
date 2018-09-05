package pl.edu.pollub.warsztaty.common.specification;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static pl.edu.pollub.warsztaty.common.specification.Constraints.EQ;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FilterBy {
    String field();
    Constraints constraints() default EQ;
}
