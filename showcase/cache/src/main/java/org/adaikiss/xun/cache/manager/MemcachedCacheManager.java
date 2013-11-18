/**
 * 
 */
package org.adaikiss.xun.cache.manager;

import java.util.Collection;

import org.springframework.cache.Cache;
import org.springframework.cache.support.AbstractCacheManager;

/**
 * @author HuLingwei
 *
 */
public class MemcachedCacheManager extends AbstractCacheManager {
	private Collection<? extends Cache> caches;

	/**
	 * Specify the collection of Cache instances to use for this CacheManager.
	 */
	public void setCaches(Collection<? extends Cache> caches) {
		this.caches = caches;
	}

	@Override
	protected Collection<? extends Cache> loadCaches() {
		return this.caches;
	}

	@Override
	public Cache getCache(String name) {
		Cache c = super.getCache(name);
		if(null == c){
			return super.getCache("default");
		}
		return c;
	}

	
}
