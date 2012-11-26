/**
 * 
 */
package org.adaikiss.xun.charge.validation.constraints;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;

import org.adaikiss.xun.charge.validation.validator.NotZeroValidator;

/**
 * valid the number field is not null or zero
 * @author hlw
 *
 */
@NotNull
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = NotZeroValidator.class)
public @interface NotZero {
	String message() default "{org.adaikiss.xun.charge.validation.constraints.NotZero.message}";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default {};

	/**
	 * Defines several <code>@NotZero</code> annotations on the same element
	 * @see org.adaikiss.xun.charge.validation.constraints.NotZero
	 *
	 * @author Emmanuel Bernard
	 */
	@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
	@Retention(RUNTIME)
	@Documented
	@interface List {
		NotZero[] value();
	}
}
