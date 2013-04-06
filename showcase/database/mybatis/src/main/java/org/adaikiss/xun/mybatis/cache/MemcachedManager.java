/**
 * 
 */
package org.adaikiss.xun.mybatis.cache;

import java.util.Properties;

import org.adaikiss.xun.mybatis.util.ApplicationUtil;

/**
 * @author hlw
 * 
 */
public class MemcachedManager {
	public static <K, V> MemcachedClient<K, V> create() {
		Properties properties = ApplicationUtil.getProperties();
		return new MemcachedClientImpl<K, V>(
				properties.getProperty("memcached.servers"));
	}
}
