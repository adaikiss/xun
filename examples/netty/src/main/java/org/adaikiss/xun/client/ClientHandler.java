/**
 * 
 */
package org.adaikiss.xun.client;

import java.nio.charset.Charset;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author hlw
 * 
 */
public class ClientHandler extends SimpleChannelUpstreamHandler {
	private static final Logger logger = LoggerFactory
			.getLogger(ClientHandler.class.getName());

	private ChannelBuffer message;

	public ClientHandler(String message) {
		if (null == message || message.length() == -1) {
			throw new IllegalArgumentException("message: blank!");
		}
		
		this.message = ChannelBuffers.wrappedBuffer(message.getBytes());
	}

	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) {
		e.getChannel().write(message);
	}

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
//		e.getChannel().write(e.getMessage());
		logger.debug(((ChannelBuffer)e.getMessage()).toString(Charset.forName("UTF-8")));
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
		// Close the connection when an exception is raised.
		logger.warn("Unexpected exception from downstream.", e.getCause());
		e.getChannel().close();
	}
}