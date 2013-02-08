package org.adaikiss.xun.cache;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

/**
 * 内存缓存，其实就是一个Map
 * @author hlw
 *
 */
@Component("memoryCache")
public class MemoryCache implements DisposableBean {
	private static final Logger logger = LoggerFactory.getLogger(MemoryCache.class);

	private Map<Serializable, Object> cache;

	private Map<Serializable, Set<Serializable>> expireMonitor;

	private Object[] cache_lock = new Object[0];
	private Object[] monitor_lock = new Object[0];

	public MemoryCache() {
		cache = new HashMap<Serializable, Object>();
		expireMonitor = new HashMap<Serializable, Set<Serializable>>();
	}

	/**
	 * 从缓存中获取对象
	 * 
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T get(Serializable key) {
		return (T) cache.get(key);
	}

	/**
	 * 把对象存到缓存中
	 * 
	 * @param key
	 * @param value
	 */
	public void set(Serializable key, Object value) {
		synchronized (cache_lock) {
			cache.put(key, value);
		}
	}

	/**
	 * 把对象存到缓存中, 同时加入expireGroup
	 * 
	 * @param key
	 * @param value
	 * @param expireGroup
	 */
	public void set(Serializable key, Object value, Serializable expireGroup) {
		set(key, value);
		addExpireListener(expireGroup, key);
	}

	/**
	 * 将缓存对象的key放到一个expire组中, 在调用expire方法的时候会清空该组中所有对象
	 * 
	 * @param expireGroup
	 * @param cacheKey
	 */
	public void addExpireListener(Serializable expireGroup, Serializable cacheKey) {
		synchronized (monitor_lock) {
			Set<Serializable> expireKeys = expireMonitor.get(expireGroup);
			if (null == expireKeys) {
				expireKeys = new HashSet<Serializable>();
			}
			expireKeys.add(cacheKey);
			expireMonitor.put(expireGroup, expireKeys);
		}
	}

	/**
	 * 清除一组缓存
	 * 
	 * @param expireGroup
	 */
	public void expireGroup(Serializable expireGroup) {
		Set<Serializable> expireKeys;
		synchronized (monitor_lock) {
			expireKeys = expireMonitor.get(expireGroup);
			expireMonitor.remove(expireGroup);
		}
		if (null != expireKeys) {
			logger.debug("开始清除内存缓存[expireGroup : {}]", expireGroup);
			synchronized (cache_lock) {
				for (Serializable expireKey : expireKeys) {
					cache.remove(expireKey);
				}
			}
		}
	}

	/**
	 * 清除一个缓存对象
	 * 
	 * @param cacheKey
	 */
	public void expire(Serializable cacheKey) {
		logger.debug("清除内存缓存[cacheKey : {}]", cacheKey);
		synchronized (cache_lock) {
			cache.remove(cacheKey);
		}
	}

	/**
	 * 清除一个缓存对象, 同时从expireGroup中移除
	 * 
	 * @param cacheKey
	 * @param expireGroup
	 */
	public void expire(Serializable cacheKey, Serializable expireGroup) {
		expire(cacheKey);
		synchronized (monitor_lock) {
			Set<Serializable> expireKeys = expireMonitor.get(expireGroup);
			if (expireKeys != null) {
				expireKeys.remove(cacheKey);
			}
		}
	}

	@Override
	public void destroy() throws Exception {
		cache.clear();
		expireMonitor.clear();
	}
}
