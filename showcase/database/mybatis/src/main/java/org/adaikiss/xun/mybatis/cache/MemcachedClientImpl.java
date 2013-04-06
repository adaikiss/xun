/**
 * 
 */
package org.adaikiss.xun.mybatis.cache;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import net.spy.memcached.AddrUtil;

/**
 * @author hlw
 *
 */
public class MemcachedClientImpl<KEY, VALUE> implements MemcachedClient<KEY, VALUE> {

	private final List<KEY> cachedKeys = new LinkedList<KEY>();

	private net.spy.memcached.MemcachedClient client;

	public MemcachedClientImpl(String serverUrl){
		try {
			this.client = new net.spy.memcached.MemcachedClient(AddrUtil.getAddresses(serverUrl));
		} catch (IOException e) {
			throw new RuntimeException("error getting memcached client!", e);
		}
	}

	@Override
	public void removeAll() {
		for(KEY key : cachedKeys){
			client.delete(String.valueOf(key.hashCode()));
			cachedKeys.remove(key);
		}
	}

	@Override
	public int getSize() {
		return cachedKeys.size();
	}

	@Override
	@SuppressWarnings("unchecked")
	public VALUE get(KEY key) {
		return (VALUE)client.get(String.valueOf(key.hashCode()));
	}
 
	@Override
	public void put(KEY key, VALUE value) {
		client.set(String.valueOf(key.hashCode()), 0, value);
	}

	@Override
	public void remove(KEY key) {
		client.delete(String.valueOf(key.hashCode()));
	}
	
}
