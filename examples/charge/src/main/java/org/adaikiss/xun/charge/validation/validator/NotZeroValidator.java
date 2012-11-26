/**
 * 
 */
package org.adaikiss.xun.charge.validation.validator;

import java.text.DecimalFormat;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.adaikiss.xun.charge.validation.constraints.NotZero;

/**
 * @author hlw
 *
 */
public class NotZeroValidator implements ConstraintValidator<NotZero, Number>{

	@Override
	public void initialize(NotZero constraintAnnotation) {
	}

	@Override
	public boolean isValid(Number value, ConstraintValidatorContext context) {
		if(value == null){
			return true;
		}
		return new DecimalFormat("0").format(value).equals("0");
	}

}
