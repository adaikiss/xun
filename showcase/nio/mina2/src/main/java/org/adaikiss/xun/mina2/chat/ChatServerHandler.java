/**
 * 
 */
package org.adaikiss.xun.mina2.chat;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

/**
 * @author hlw
 *
 */
public class ChatServerHandler extends IoHandlerAdapter {

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		System.out.println("A client connected!");
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		System.out.println(session.getAttribute("name") + " left!");
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status)
			throws Exception {
		System.out.println("IDLE:" + session.getIdleCount(status));
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		cause.printStackTrace();
	}

	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		if("quit".equalsIgnoreCase(message.toString().trim())){
			session.close(true);
		}
		System.out.println(message);
	}

}
