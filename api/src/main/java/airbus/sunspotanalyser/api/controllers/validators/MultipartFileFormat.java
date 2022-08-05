package airbus.sunspotanalyser.api.controllers.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = MultipartFileFormatValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface MultipartFileFormat {

	String message() default "must be a valid file (max size, name length, name pattern)";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	long maxSize() default -1;

	int maxNameLength() default -1;

	String regexp() default "";

}
