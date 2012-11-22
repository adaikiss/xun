/**
 * 
 */
package org.adaikiss.xun.security;

import java.util.Collection;
import java.util.Set;

import org.adaikiss.xun.cache.memcached.SpyMemcachedClient;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.subject.SimplePrincipalCollection;

/**
 * @author hlw
 * 
 */
public class SpyMemcachedCache<K, V> implements Cache<K, V> {

	public static final String KEY_PREFIX = "shiro_";

	private SpyMemcachedClient client;

	// private MessagePack globalMsgPack = new MessagePack();
	//
	// private SimpleSessionTemplate simpleSessionTemplate = new
	// SimpleSessionTemplate();
	// private SimplePrincipalCollectionTemplate simplePrincipalCollection = new
	// SimplePrincipalCollectionTemplate();

	public SpyMemcachedCache(SpyMemcachedClient client) {
		this.client = client;
		// globalMsgPack = new MessagePack();
		// globalMsgPack.register(SimpleSession.class, simpleSessionTemplate);
		// globalMsgPack.register(SimplePrincipalCollection.class,
		// simplePrincipalCollection);
	}

	// private <T> byte[] pack(T t) {
	// try {
	// return globalMsgPack.write(t);
	// } catch (IOException e) {
	// throw new RuntimeException("msgpack压缩时出现异常！");
	// }
	// }
	//
	// @SuppressWarnings("unchecked")
	// private <T> T unpack(Object o) {
	// if(null == o){
	// return null;
	// }
	// try {
	// return (T)globalMsgPack.read((byte[])o, simpleSessionTemplate);
	// } catch (IOException e) {
	// throw new RuntimeException("msgpack解压缩时出现异常！");
	// }
	// }

	private String getKey(K k) {
		if (k instanceof SimplePrincipalCollection) {
			return String.valueOf(((SimplePrincipalCollection) k)
					.getPrimaryPrincipal());
		}
		return String.valueOf(k);
	}

	@Override
	public V get(K key) throws CacheException {
		// return unpack(client.get(KEY_PREFIX + getKey(key)));
		return client.get(KEY_PREFIX + getKey(key));
	}

	@Override
	public V put(K key, V value) throws CacheException {
		V oldValue = get(key);
		// boolean result = client.safeSet(KEY_PREFIX + getKey(key), 0,
		// pack(value));
		boolean result = client.safeSet(KEY_PREFIX + getKey(key), 0, value);
		if (!result) {
			throw new CacheException("");
		}
		return oldValue;
	}

	@Override
	public V remove(K key) throws CacheException {
		V oldValue = get(key);
		client.delete(KEY_PREFIX + getKey(key));
		return oldValue;
	}

	@Override
	public void clear() throws CacheException {
		throw new UnsupportedOperationException();
	}

	@Override
	public int size() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Set<K> keys() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Collection<V> values() {
		throw new UnsupportedOperationException();
	}

}
