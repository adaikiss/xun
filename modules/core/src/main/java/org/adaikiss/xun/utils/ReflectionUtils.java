package org.adaikiss.xun.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReflectionUtils {
	private static final Logger logger = LoggerFactory.getLogger(ReflectionUtils.class);
	public static Object getBeanValue(Object obj, String propertyName){
		try {
			Object value = obj.getClass().getDeclaredMethod(getGetName(propertyName)).invoke(obj);
			return value;
		} catch (NoSuchMethodException e) {
			try {
				return obj.getClass().getDeclaredField(propertyName).get(obj);
			} catch (Exception e1) {
				if(logger.isErrorEnabled()){
					logger.error("属性" + propertyName + "不存在!", e1);
				}
			}
		} catch (Exception e) {
			logger.error("", e);
		}
		return null;
	}

	private static String getGetName(String propertyName){
		if(null == propertyName){
			return null;
		}
		StringBuilder sb = new StringBuilder().append("get").append(propertyName.substring(0, 1).toUpperCase());
		if(propertyName.length() > 1){
			sb.append(propertyName.substring(1));
		}
		return sb.toString();
	}
}
