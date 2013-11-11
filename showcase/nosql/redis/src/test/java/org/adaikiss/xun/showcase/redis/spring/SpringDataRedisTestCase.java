/**
 * 
 */
package org.adaikiss.xun.showcase.redis.spring;

import java.util.List;

import javax.annotation.Resource;

import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * @author HuLingwei
 * 
 */
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class SpringDataRedisTestCase extends AbstractJUnit4SpringContextTests {
	@Autowired
	protected RedisTemplate<String, String> template;
	@Resource(name = "redisTemplate")
	protected ListOperations<String, String> listOps;

	@Before
	public void setUp() throws Exception {
		template.getConnectionFactory().getConnection().flushAll();
	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void testListOps() {
		String key = "sloo";
		listOps.leftPushAll(key, "a", "b", "c", "d");
		List<String> actual = listOps.range(key, 0, -1);
		Assert.assertThat(actual.size(), CoreMatchers.is(4));
		Assert.assertThat(actual, CoreMatchers.hasItems("a", "b", "c", "d"));
	}

	@Test
	public void testTemplate() {
		String key = "sloo";
		template.boundListOps(key).leftPushAll("e", "f", "g", "h");
		List<String> actual = template.boundListOps(key).range(0, -1);
		Assert.assertThat(actual.size(), CoreMatchers.is(4));
		Assert.assertThat(actual, CoreMatchers.hasItems("e", "f", "g", "h"));
	}
}
