/**
 * 
 */
package org.adaikiss.xun.servlet3.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.adaikiss.xun.servlet3.SessionHolder;

/**
 * @author hlw
 *
 */
@WebListener
public class SessionCountListener implements HttpSessionListener{
	
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		SessionHolder.addSession(se.getSession());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		SessionHolder.removeSession(se.getSession().getId());
	}
}
