package org.adaikiss.xun.cache.spring.repository.aspectj;

import org.adaikiss.xun.cache.spring.repository.CacheTestCase;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles({ "aspectj", "redis", "jedis" })
public class JedisCacheTest extends CacheTestCase {
	@Autowired
	private RedisTemplate<String, String> template;

	@Before
	public void setUp() throws Exception{
		template.getConnectionFactory().getConnection().flushAll();
	}
}
