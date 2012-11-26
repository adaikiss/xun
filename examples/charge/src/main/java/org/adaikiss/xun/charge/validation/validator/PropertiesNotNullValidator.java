/**
 * 
 */
package org.adaikiss.xun.charge.validation.validator;

import java.util.Collection;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.adaikiss.xun.charge.validation.constraints.PropertiesNotNull;
import org.adaikiss.xun.utils.ReflectionUtils;

/**
 * @author hlw
 * 
 */
public class PropertiesNotNullValidator implements ConstraintValidator<PropertiesNotNull, Object> {

	private String[] propertyNames;

	@Override
	public void initialize(PropertiesNotNull constraintAnnotation) {
		propertyNames = constraintAnnotation.propertyNames();
	}

	@Override
	@SuppressWarnings("rawtypes")
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		if (null == value) {
			return true;
		}
		if (Collection.class.isAssignableFrom(value.getClass())) {
			for (Object o : (Collection) value) {
				if (!isPropertiesNotNull(o)) {
					return false;
				}
			}
			return true;
		}
		return isPropertiesNotNull(value);
	}

	private boolean isPropertiesNotNull(Object value) {
		try {
			for (String propertyName : propertyNames) {
				if (null == ReflectionUtils.getBeanValue(value, propertyName)) {
					return false;
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return true;
	}
}
