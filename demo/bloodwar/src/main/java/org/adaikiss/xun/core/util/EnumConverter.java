/**
 * 
 */
package org.adaikiss.xun.core.util;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

//import org.adaikiss.xun.cms.enumeration.PostStatus;
import org.adaikiss.xun.core.enumeration.UserStatus;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.AbstractConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @author hlw
 * 
 */
@Component
@Lazy(false)
public class EnumConverter extends AbstractConverter{

	private static final Logger logger = LoggerFactory
			.getLogger(EnumConverter.class);

	private static Map<String, Class<? extends Enum<?>>> enums = new HashMap<String, Class<? extends Enum<?>>>(
			10);
	static {
//		addEnum(PostStatus.class);
		addEnum(UserStatus.class);
	}

	@PostConstruct
	public void register(){
		ConvertUtils.register(this, Enum.class);
	}

	private static void addEnum(Class<? extends Enum<?>> type) {
		enums.put(type.getSimpleName(), type);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static Enum convert(String value) {
		if (null == value) {
			return null;
		}
		int position = value.indexOf(".");
		if (position == -1) {
			logger.warn("Error while converting enum, parameter[" + value
					+ "] format error, must contains \".\"");
			return null;
		}
		String typeName = value.substring(0, position);
		String name = value.substring(position + 1);
		Class<? extends Enum> e = enums.get(typeName);
		if (null == e) {
			logger.warn("Error while converting enum, no suitable Enum type ["
					+ typeName + "]");
			return null;
		}
		Enum<?> en = Enum.valueOf(e, name);
		return en;
	}

	@Override
	@SuppressWarnings("rawtypes")
	protected Object convertToType(Class type, Object value) throws Throwable {
		if(null == value){
			return null;
		}
		if(!String.class.equals(value.getClass())){
			return null;
		}
		return convert((String)value);
	}

	@Override
	@SuppressWarnings("rawtypes")
	protected Class getDefaultType() {
		return Enum.class;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

}
