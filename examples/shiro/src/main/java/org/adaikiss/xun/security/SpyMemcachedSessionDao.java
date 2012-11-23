/**
 * 
 */
package org.adaikiss.xun.security;

import java.io.Serializable;
import java.util.Collection;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.cache.memcached.SpyMemcachedClient;

/**
 * @author hlw
 *
 */
//not use!
public class SpyMemcachedSessionDao extends AbstractSessionDAO {

	@Autowired
	private SpyMemcachedClient client;

	@Override
	public void update(Session session) throws UnknownSessionException {
		String key = String.valueOf(session.getId());
		if(!client.safeSet(key, 0, session)){
			throw new RuntimeException("session更新失败！");
		}
	}

	@Override
	public void delete(Session session) {
		String key = String.valueOf(session.getId());
		if(!client.safeDelete(key)){
			throw new RuntimeException("session删除失败！");
		}
	}

	@Override
	public Collection<Session> getActiveSessions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Serializable doCreate(Session session) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Session doReadSession(Serializable sessionId) {
		// TODO Auto-generated method stub
		return null;
	}
}
