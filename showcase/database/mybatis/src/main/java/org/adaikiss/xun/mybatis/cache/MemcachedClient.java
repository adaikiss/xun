/**
 * 
 */
package org.adaikiss.xun.mybatis.cache;

/**
 * @author hlw
 *
 */
public interface MemcachedClient<KEY, VALUE> {
	void removeAll();

	int getSize();

	VALUE get(KEY key);

	void put(KEY key, VALUE value);

	void remove(KEY key);
}
