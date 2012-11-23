package org.springside.modules.memcached;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springside.modules.cache.memcached.SpyMemcachedClient;

import com.google.common.collect.Lists;

/**
 * from springside4
 * @author calvin
 *
 */
@Profile("spymemcached")
@ContextConfiguration(locations = { "classpath:applicationContext-memcached.xml" })
public class SpyMemcachedClientTest extends AbstractJUnit4SpringContextTests{

	@Autowired
	private SpyMemcachedClient client;

	@Test
	public void normal() throws Exception{

		String key = "consumer:1";
		String value = "admin";

		String key2 = "consumer:2";
		String value2 = "user";

		//get/set
		client.set(key, 60 * 60 * 1, value);
		Thread.sleep(1000);
		String result = client.get(key);
		assertEquals(value, result);

		//safeSet
		client.safeSet(key2, 60 * 60 * 1, value2);
		result = client.get(key2);
		assertEquals(value2, result);

		//bulk
		Map<String, Object> bulkResult = client.getBulk(Lists.newArrayList(key, key2));
		assertEquals(2, bulkResult.size());
		assertEquals(value, bulkResult.get(key));

		//delete
		client.delete(key);
		Thread.sleep(1000);
		result = client.get(key);
		assertNull(result);

		client.safeDelete(key);
		result = client.get(key);
		assertNull(result);
	}

	@Test
	public void incr() {
		String key = "counter";

		assertEquals(1, client.incr(key, 1, 1));
		//注意counter的实际类型是String
		assertEquals("1", client.get(key));

		assertEquals(2, client.incr(key, 1, 1));
		assertEquals("2", client.get(key));

		assertEquals(0, client.decr(key, 2, 1));
		assertEquals("0", client.get(key));

	}
}
