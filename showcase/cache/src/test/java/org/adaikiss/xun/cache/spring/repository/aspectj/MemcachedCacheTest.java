package org.adaikiss.xun.cache.spring.repository.aspectj;

import net.rubyeye.xmemcached.MemcachedClient;

import org.adaikiss.xun.cache.spring.repository.CacheTestCase;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles({ "aspectj", "memcached", "xmemcached" })
public class MemcachedCacheTest extends CacheTestCase {
	@Autowired
	private MemcachedClient memcachedClient;

	@Before
	public void setUp() throws Exception{
		memcachedClient.flushAll();
	}
}
