package org.adaikiss.xun.security;

import org.adaikiss.xun.cache.MemoryCache;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author hlw
 *
 */
public class ClearMemoryCacheSessionListener extends SessionListenerAdapter {

	@Autowired
	private MemoryCache cache;

	@Override
	public void onExpiration(Session session) {
		// 当session失效时, 所有属于该组(sessionId)的内存缓存都将被清除
		cache.expireGroup(session.getId());
		super.onExpiration(session);
	}

}
