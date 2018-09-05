package pl.edu.pollub.warsztaty.common.validation;

import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static java.util.Objects.isNull;

class StartsWithSameLetterValidator implements ConstraintValidator<StartsWithSameLetter, Object> {

    private String name;
    private String category;
    private String message;

    @Override
    public void initialize(StartsWithSameLetter constraintAnnotation) {
        this.name = constraintAnnotation.name();
        this.category = constraintAnnotation.category();
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        this.displayAsFieldError(context);
        String name = (String)(new BeanWrapperImpl(value).getPropertyValue(this.name));
        String category = (String)(new BeanWrapperImpl(value).getPropertyValue(this.category));

        if(isNull(name) || isNull(category)) {
            return false;
        }

        return name.charAt(0) == category.charAt(0);
    }

    private void displayAsFieldError(ConstraintValidatorContext context){
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(this.message).addPropertyNode("category").addConstraintViolation();
    }
}
