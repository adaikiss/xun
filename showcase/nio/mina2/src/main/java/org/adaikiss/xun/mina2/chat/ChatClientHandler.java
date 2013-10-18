/**
 * 
 */
package org.adaikiss.xun.mina2.chat;

import org.adaikiss.xun.netty.chat.proto.MessageProto.Message;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

/**
 * @author hlw
 *
 */
public class ChatClientHandler extends IoHandlerAdapter {

	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		cause.printStackTrace();
	}

	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		Message m = (Message) message;
		System.out.println(m.getUser().getName() + ":" + m.getContent());
	}

}
