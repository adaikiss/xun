/**
 * 
 */
package org.adaikiss.xun.mybatis.util;

import java.io.IOException;
import java.io.InputStream;

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
	private static final SqlSessionFactory sqlSessionFactory;
	public static final String MYBATIS_CONFIG_FILE = "mybatis-config.xml";
	static {
		try {
			InputStream inputStream = Resources
					.getResourceAsStream(MYBATIS_CONFIG_FILE);
			sqlSessionFactory = new SqlSessionFactoryBuilder()
					.build(inputStream);
		} catch (IOException e) {
			logger.error("Initial SessionFactory creation failed.", e);
			throw new ExceptionInInitializerError(e);
		}
	}

	public static SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}
}
