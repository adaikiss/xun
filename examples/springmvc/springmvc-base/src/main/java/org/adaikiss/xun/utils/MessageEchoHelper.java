/**
 * 
 */
package org.adaikiss.xun.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

/**
 * @author hlw
 *
 */
public class MessageEchoHelper {

	public static Map<String, Object> reform(List<FieldError> errors){
		Map<String, Object> errorMap = new HashMap<String, Object>();
		for(FieldError error : errors){
			errorMap.put(error.getField(), error.getDefaultMessage());
		}
		return errorMap;
	}

	public static String echo(List<ObjectError> errors){
		StringBuilder sb = new StringBuilder("errors:[");
		for(ObjectError error : errors){
			if(FieldError.class.isAssignableFrom(error.getClass())){
				FieldError fieldError = (FieldError)error;
				fieldError.getField();
				fieldError.getDefaultMessage();
				sb.append(fieldError.getObjectName()).append(".").append(fieldError.getField());
			}else{
				sb.append(error.getObjectName());
			}
			sb.append(":").append(error.getDefaultMessage()).append(", ");
		}
		if(sb.charAt(sb.length() - 1) == ' '){
			sb.delete(sb.length() - 2, sb.length());
		}
		sb.append("]");
		return sb.toString();
	}
}
