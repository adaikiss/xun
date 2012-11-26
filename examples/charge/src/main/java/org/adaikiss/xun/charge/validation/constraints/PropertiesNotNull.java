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

import org.adaikiss.xun.charge.validation.validator.PropertiesNotNullValidator;

/**
 * The properties of the annotated element must not be null. Accepts any type.
 * 
 * @author hlw
 *
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = PropertiesNotNullValidator.class)
public @interface PropertiesNotNull {
	String message() default "{org.adaikiss.xun.charge.validation.constraints.PropertiesNotNull.message}";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default {};

	String[] propertyNames() default {};

	/**
	 * Defines several <code>@PropertiesNotNull</code> annotations on the same element
	 * @see org.adaikiss.xun.charge.validation.constraints.PropertiesNotNull
	 *
	 * @author Emmanuel Bernard
	 */
	@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
	@Retention(RUNTIME)
	@Documented
	@interface List {
		PropertiesNotNull[] value();
	}
}
