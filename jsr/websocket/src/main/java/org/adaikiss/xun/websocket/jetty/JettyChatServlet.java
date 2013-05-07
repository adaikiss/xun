/**
 * 
 */
package org.adaikiss.xun.websocket.jetty;

import javax.servlet.annotation.WebServlet;

import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

/**
 * @author hlw
 *
 */
@SuppressWarnings("serial")
@WebServlet(name = "JettyChatServlet", urlPatterns = { "/chat.svl" })
public class JettyChatServlet extends WebSocketServlet {

	@Override
	public void configure(WebSocketServletFactory factory) {
		factory.register(JettyChatSocket.class);
	}

}
