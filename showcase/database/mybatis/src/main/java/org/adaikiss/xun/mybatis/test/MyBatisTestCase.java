/**
 * 
 */
package org.adaikiss.xun.mybatis.test;

import java.util.Properties;

import org.adaikiss.xun.mybatis.util.ApplicationUtil;
import org.adaikiss.xun.mybatis.util.MyBatisUtil;
import org.adaikiss.xun.mybatis.util.SqlUtil;
import org.apache.ibatis.session.SqlSessionFactory;
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
	protected String config = "database/database.sql";

	@Before
	public void setUp() throws Exception {
		Properties properties = ApplicationUtil.getProperties();
		SqlUtil.runScript(properties.getProperty("jdbc.url"),
				properties.getProperty("jdbc.username"),
				properties.getProperty("jdbc.password"), properties.getProperty("jdbc.driver"), sqlFile);
		MyBatisUtil.init(properties);
		sqlSessionFactory = MyBatisUtil.getSqlSessionFactory();
	}

	@After
	public void tearDown() throws Exception {

	}

}
