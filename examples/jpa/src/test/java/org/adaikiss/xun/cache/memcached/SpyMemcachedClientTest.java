/**
 * 
 */
package org.adaikiss.xun.cache.memcached;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.google.common.collect.Lists;

/**
 * @author hlw
 *
 */
@ActiveProfiles({ "test", "spymemcached" })
@ContextConfiguration(locations = { "classpath:applicationContext.xml",
		"classpath:applicationContext-memcached.xml" })
public class SpyMemcachedClientTest extends AbstractJUnit4SpringContextTests{
	@Autowired
	SpyMemcachedClient client;

	@Test
	public void normal() throws Exception{
		String key1 = "test:1";
		String value1 = "test one!";
		String key2 = "test:2";
		String value2 = "test two!";

		//get/set
		client.set(key1, 0, value1);
		Thread.sleep(1000);
		Assert.assertEquals(value1, client.get(key1));

		//safeSet
		client.safeSet(key2, 60 * 60 * 1, value2);
		Assert.assertEquals(value2, client.get(key2));

		//bulk
		Map<String, Object> bulkResult = client.getBulk(Lists.newArrayList(key1, key2));
		Assert.assertEquals(2, bulkResult.size());
		Assert.assertEquals(value1, bulkResult.get(key1));

		//delete
		client.delete(key1);
		Thread.sleep(1000);
		Assert.assertNull(client.get(key1));

		client.safeDelete(key2);
		Assert.assertNull(client.get(key2));
	}

	@Test
	public void incr() {
		String key = "counter";

		Assert.assertEquals(1, client.incr(key, 1, 1));
		//注意counter的实际类型是String
		Assert.assertEquals("1", client.get(key));

		Assert.assertEquals(2, client.incr(key, 1, 1));
		Assert.assertEquals("2", client.get(key));

		Assert.assertEquals(0, client.decr(key, 2, 1));
		Assert.assertEquals("0", client.get(key));

	}
}
