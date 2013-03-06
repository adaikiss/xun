/**
 * 
 */
package org.adaikiss.xun.servlet3;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

/**
 * @author hlw
 * 
 */
public class SessionHolder {
	private static Map<String, HttpSession> sessions = new HashMap<String, HttpSession>();

	public static int count() {
		return sessions.size();
	}

	public static void addSession(HttpSession session) {
		sessions.put(session.getId(), session);
	}

	public static HttpSession removeSession(String sessionId) {
		return sessions.remove(sessionId);
	}
}
