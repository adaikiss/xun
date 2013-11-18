/**
 * 
 */
package org.adaikiss.xun.cache.manager;

import java.net.URISyntaxException;

import net.rubyeye.xmemcached.MemcachedClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.util.Assert;

/**
 * @author HuLingwei
 * 
 */
public class MemcachedCache implements Cache {

	private MemcachedClient cache;
	private String name;
	private static final Logger logger = LoggerFactory
			.getLogger(MemcachedCache.class);

	public MemcachedCache(final String name,
			final MemcachedClient client) throws URISyntaxException {
		this.name = name;
		this.cache = client;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Object getNativeCache() {
		return cache;
	}

	@Override
	public ValueWrapper get(final Object key) {
		Object value = null;
		try {
			value = cache.get(key.toString());
		} catch (final Exception e) {
			logger.error("error getting cache [" + key + "]", e);
		}
		if (value == null) {
			return null;
		}
		return new SimpleValueWrapper(value);
	}

	@Override
	public void put(final Object key, final Object value) {
		if(null == value){
			return;
		}
		try {
			cache.set(key.toString(), 7 * 24 * 3600, value);
		} catch (Exception e) {
			logger.error(new StringBuilder("error putting cache [").append(key)
					.append("=").append(value).append("]").toString(), e);
		}
		Assert.notNull(get(key)); // This fails on the viewCache
	}

	@Override
	public void evict(final Object key) {
		try {
			this.cache.delete(key.toString());
		} catch (Exception e) {
			logger.error("error evicting cache [" + key + "]", e);
		}
	}

	@Override
	public void clear() {
		try {
			cache.flushAll();
		} catch (Exception e) {
			logger.error("error clearing cache!", e);
		}
	}
}