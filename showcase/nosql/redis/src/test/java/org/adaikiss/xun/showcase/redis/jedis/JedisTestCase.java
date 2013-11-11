/**
 * 
 */
package org.adaikiss.xun.showcase.redis.jedis;

import java.util.Properties;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import redis.clients.jedis.Jedis;

/**
 * @author HuLingwei
 *
 */
public class JedisTestCase {
	protected static Jedis jedis;
	@BeforeClass
	public static void beforeClass()throws Exception{
		Properties props = PropertiesLoaderUtils.loadAllProperties("application.test.properties");
		jedis = new Jedis(props.getProperty("redis.server.host"), Integer.parseInt(props.getProperty("redis.server.port")));
	}

	@AfterClass
	public static void afterClass() throws Exception{
		if ( jedis != null ) {
			jedis.disconnect();
		}
	}

	@Before
	public void setUp(){
		jedis.flushAll();
	}
}
