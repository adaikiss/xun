package org.adaikiss.xun.struts2.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.opensymphony.xwork2.validator.ValidationException;
import com.opensymphony.xwork2.validator.validators.FieldValidatorSupport;

/**
 * 
 * @author hlw
 *
 */
public class PhoneOrMobileValidator extends FieldValidatorSupport {
	private static String regex = "(^0(([1-9]\\d)|([3-9]\\d{2}))\\d{8}$)|(^1\\d{10}$)";

	private boolean doTrim = true;

	public void validate(Object object) throws ValidationException {
		String fieldName = getFieldName();
		String val = (String) this.getFieldValue(fieldName, object);

		if (val == null || val.length() <= 0) {
			// use a required validator for these
			return;
		}
		if (doTrim) {
			val = val.trim();
			if (val.length() <= 0) {
				// use a required validator
				return;
			}
		}
		if (!matches(val)) {
			addFieldError(fieldName, object);
		}
	}

	private static boolean matches(String input) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		return matcher.matches();
	}

	public boolean isDoTrim() {
		return doTrim;
	}

	public void setDoTrim(boolean doTrim) {
		this.doTrim = doTrim;
	}

	public static void main(String[] args) {
		System.out.println(matches("13333333333"));
		System.out.println(matches("1333333333"));
		System.out.println(matches("057111112024"));
	}
}
