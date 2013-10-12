/**
 * 
 */
package org.adaikiss.xun.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author hlw
 *
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {

	private static final Logger logger = LoggerFactory.getLogger(ServerHandler.class
			.getName());

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		ctx.write("pong");
	}


	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}


	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		// Close the connection when an exception is raised.
		logger.error("Unexpected exception from downstream.",
				cause.getCause());
		ctx.close();
	}
}