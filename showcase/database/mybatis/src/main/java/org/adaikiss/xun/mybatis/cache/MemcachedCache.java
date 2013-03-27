/**
 * 
 */
package org.adaikiss.xun.mybatis.cache;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.ibatis.cache.Cache;
import org.apache.ibatis.cache.CacheException;

/**
 * @author hlw
 * 
 */
public class MemcachedCache implements Cache {

	/**
	 * The cache manager reference.
	 */
	private static MemcachedClient<String, Object> client = MemcachedManager.create();

	/**
	 * The {@code ReadWriteLock}.
	 */
	private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

	/**
	 * The cache id.
	 */
	private final String id;

	/**
	 * 
	 * 
	 * @param id
	 */
	public MemcachedCache(final String id) {
		if (id == null) {
			throw new IllegalArgumentException("Cache instances require an ID");
		}
		this.id = id;
	}

	/**
	 * {@inheritDoc}
	 */
	public void clear() {
		client.removeAll();
	}

	/**
	 * {@inheritDoc}
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * {@inheritDoc}
	 */
	public Object getObject(Object key) {
		try {
			return client.get(String.valueOf(key));
		} catch (Throwable t) {
			throw new CacheException(t);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public ReadWriteLock getReadWriteLock() {
		return this.readWriteLock;
	}

	/**
	 * {@inheritDoc}
	 */
	public int getSize() {
		try {
			return client.getSize();
		} catch (Throwable t) {
			throw new CacheException(t);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void putObject(Object key, Object value) {
		try {
			client.put(String.valueOf(key), value);
		} catch (Throwable t) {
			throw new CacheException(t);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public Object removeObject(Object key) {
		try {
			Object obj = this.getObject(key);
			client.remove(String.valueOf(key));
			return obj;
		} catch (Throwable t) {
			throw new CacheException(t);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Cache)) {
			return false;
		}

		Cache otherCache = (Cache) obj;
		return this.id.equals(otherCache.getId());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return this.id.hashCode();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "MemcachedCache {" + this.id + "}";
	}
}