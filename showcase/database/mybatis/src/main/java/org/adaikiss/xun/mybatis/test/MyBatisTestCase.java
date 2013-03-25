/**
 * 
 */
package org.adaikiss.xun.mybatis.test;

import java.io.InputStream;

import org.adaikiss.xun.mybatis.util.MyBatisUtil;
import org.adaikiss.xun.mybatis.util.SqlUtil;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;

/**
 * @author hlw
 * 
 */
public class MyBatisTestCase {
	protected SqlSessionFactory sqlSessionFactory;
	protected Class<? extends java.sql.Driver> driver = org.h2.Driver.class;
	protected String sqlFile = "database/database.sql";

	@Before
	public void setUp() throws Exception {
		Configuration config = new PropertiesConfiguration(
				"application.properties");
		SqlUtil.runScript(config.getString("jdbc.url"),
				config.getString("jdbc.username"),
				config.getString("jdbc.password"), config.getString("jdbc.driver"), sqlFile);
		InputStream inputStream = Resources
				.getResourceAsStream(MyBatisUtil.MYBATIS_CONFIG_FILE);
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}

	@After
	public void tearDown() throws Exception {

	}

}
