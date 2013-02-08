package org.adaikiss.xun.utils;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.Assert;
import org.springside.modules.utils.spring.SpringContextHolder;

/**
 * @author hlw
 *
 */
public class SystemManager implements InitializingBean {
	@Autowired
	private ResourceLoader resourceLoader;

	private static final Logger logger = LoggerFactory.getLogger(SystemManager.class);
	private String[] configs;

	private Properties props;

	private static SystemManager systemManager = null;

	public SystemManager() {
	}

	public static SystemManager getInstance() {
		if (null == systemManager) {
			systemManager = SpringContextHolder.getBean(SystemManager.class);
		}
		return systemManager;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(configs, "配置文件路径不能为空");
		props = new Properties();
		for (String config : configs) {
			PropertiesLoaderUtils.fillProperties(props, resourceLoader.getResource(config));
		}
		logger.debug(props.toString());
	}

	/**
	 * 获取配置信息
	 * 
	 * @param key
	 * @return
	 */
	public String get(String key) {
		return props.getProperty(key);
	}

	/**
	 * 获取配置信息
	 * 
	 * @param key
	 * @return
	 */
	public static String getProperty(String key) {
		return getInstance().get(key);
	}

	/**
	 * 获取配置信息
	 * 
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public String get(String key, String defaultValue) {
		return props.getProperty(key, defaultValue);
	}

	/**
	 * 获取配置信息
	 * 
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static String getProperty(String key, String defaultValue) {
		return getInstance().get(key, defaultValue);
	}

	public String[] getConfigs() {
		return configs;
	}

	public void setConfigs(String[] configs) {
		this.configs = configs;
	}

}
