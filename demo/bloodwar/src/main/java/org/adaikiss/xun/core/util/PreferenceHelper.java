/**
 * 
 */
package org.adaikiss.xun.core.util;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Properties;

import org.adaikiss.xun.core.model.Preference;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.support.PropertiesLoaderUtils;

/**
 * @author hlw
 *
 */
public class PreferenceHelper {
	private static final Logger logger = LoggerFactory.getLogger(PreferenceHelper.class);

	private static final String PREFERENCE_FILE_LOCATION = "preference.properties";

	private static volatile Preference preference;

	/**
	 * 获取系统设置
	 * @return
	 */
	public static Preference getPreference(){
		//volatile+double check lock singleton pattern(works with jdk6 or newer version)
		if(null == preference){
			synchronized(PreferenceHelper.class){
				if(null == preference){
					try {
						read();
					} catch (IOException e) {
						logger.error("error reading preference file!", e);
					}
				}
			}
		}
		return preference;
	}

	private static void read() throws IOException{
		Properties props = PropertiesLoaderUtils.loadAllProperties(PREFERENCE_FILE_LOCATION);
		preference = new Preference();
		injectProperties(props);
		props.clear();
	}

	private static void injectProperties(Properties props){
		Method[] methods = Preference.class.getDeclaredMethods();
		ConvertUtils.register(new DateConverter(null), Date.class);
		for(Method method : methods){
			String methodName = method.getName();
			if(!methodName.startsWith("set")){
				continue;
			}
			String name = methodName.substring(3).toLowerCase();
			Class<?> paramType = method.getParameterTypes()[0];
			Object value = ConvertUtils.convert(props.getProperty(name), paramType);
			try {
				method.invoke(preference, value);
			} catch (IllegalAccessException|IllegalArgumentException|InvocationTargetException e) {
				logger.warn(new StringBuilder().append("exception while setting preference field[").append(name).append("] with value[").append(value).append("]").toString() , e);
			}
		}
	}

	/**
	 * 将preference中的属性生成properties类型的字符串
	 * @return
	 */
	private static String toPropertiesString(){
		StringBuilder sb = new StringBuilder();
		Method[] methods = Preference.class.getDeclaredMethods();
		for(Method method : methods){
			String methodName = method.getName();
			if(!methodName.startsWith("get")){
				continue;
			}
			String name = methodName.substring(3).toLowerCase();
			Class<?> paramType = method.getReturnType();
			sb.append(name).append("=");
			try {
				Object value = method.invoke(preference);
				sb.append(toString(value, paramType));
			} catch (IllegalAccessException|IllegalArgumentException|InvocationTargetException e) {
				logger.warn(new StringBuilder().append("exception while getting preference field[").append(name).append("]").toString() , e);
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	/**
	 * 将preference中的数据写到文件中
	 */
	public synchronized static void flush(){
		toPropertiesString();
	}

	private static String toString(Object obj, Class<?> paramType){
		if(null == obj){
			return "";
		}
		if(Date.class.equals(paramType)){
			return String.valueOf(((Date)obj).getTime());
		}
		return obj.toString();
	}
}
