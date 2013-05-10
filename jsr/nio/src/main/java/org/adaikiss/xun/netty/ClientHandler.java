/**
 * 
 */
package org.adaikiss.xun.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundMessageHandlerAdapter;
import io.netty.channel.ChannelHandler.Sharable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author hlw
 *
 */
@Sharable
public class ClientHandler extends ChannelInboundMessageHandlerAdapter<String> {

	private static final Logger logger = LoggerFactory.getLogger(ClientHandler.class);

	@Override
	public void messageReceived(ChannelHandlerContext ctx, String msg)
			throws Exception {
		System.out.println("Client:received[" + msg + "]");
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		logger.error("exception", cause);
		ctx.close();
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		ctx.channel().write("hello!");
		ctx.flush();
		logger.debug("connected!");
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		logger.debug("closed!");
	}

}
