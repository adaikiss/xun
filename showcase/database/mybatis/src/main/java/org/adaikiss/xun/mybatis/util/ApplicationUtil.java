/**
 * 
 */
package org.adaikiss.xun.mybatis.util;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.ibatis.io.Resources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author hlw
 *
 */
public class ApplicationUtil {
	private static final Logger logger = LoggerFactory.getLogger(ApplicationUtil.class);

	private static final String APPLICATION_CONFIG_FILE = "application.properties";
	private static final Properties properties = new Properties();
	private static AtomicBoolean loaded = new AtomicBoolean(Boolean.FALSE);

	public static Properties getProperties(){
		if(!loaded.get()){
			try {
				properties.load(Resources.getResourceAsStream(APPLICATION_CONFIG_FILE));
				loaded.set(Boolean.TRUE);
			} catch (IOException e) {
				logger.error("error loading application properties!", e);
			}
		}
		return properties;
	}
}
