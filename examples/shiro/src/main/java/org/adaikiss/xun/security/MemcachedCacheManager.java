/**
 * 
 */
package org.adaikiss.xun.security;

import org.adaikiss.xun.cache.memcached.SpyMemcachedClient;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author hlw
 *
 */
public class MemcachedCacheManager implements CacheManager {
	@Autowired
	private SpyMemcachedClient client;

	@Override
	public <K, V> Cache<K, V> getCache(String name) throws CacheException {
		return (Cache<K, V>)new SpyMemcachedCache<K, V>(client);
	}

	public SpyMemcachedClient getClient() {
		return client;
	}

	public void setClient(SpyMemcachedClient client) {
		this.client = client;
	}

}
