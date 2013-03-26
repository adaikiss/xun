/**
 * 
 */
package org.adaikiss.xun.mybatis.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author hlw
 * 
 */
public class MyBatisUtil {
	private static final Logger logger = LoggerFactory
			.getLogger(MyBatisUtil.class);
	private static SqlSessionFactory sqlSessionFactory;
	public static final String MYBATIS_CONFIG_FILE = "mybatis-config.xml";
	public static final String APPLICATION_CONFIG_FILE = "application.properties";

	private static boolean inited = false;

	private MyBatisUtil(){
	}

	public static synchronized void init(Properties properties){
		try {
			InputStream inputStream = Resources
					.getResourceAsStream(MYBATIS_CONFIG_FILE);
			sqlSessionFactory = new SqlSessionFactoryBuilder()
					.build(inputStream, properties);
			inited = true;
		} catch (IOException e) {
			logger.error("Initial SessionFactory creation failed.", e);
			throw new ExceptionInInitializerError(e);
		}
	}

	public static SqlSessionFactory getSqlSessionFactory() {
		if(inited){
			return sqlSessionFactory;
		}
		throw new RuntimeException("not yet initialized!");
	}
}
