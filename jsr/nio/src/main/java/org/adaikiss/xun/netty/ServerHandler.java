/**
 * 
 */
package org.adaikiss.xun.netty;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundMessageHandlerAdapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author hlw
 *
 */
@Sharable
public class ServerHandler extends ChannelInboundMessageHandlerAdapter<String> {

	private static final Logger logger = LoggerFactory.getLogger(ServerHandler.class);

	@Override
	public void messageReceived(ChannelHandlerContext ctx, String msg)
			throws Exception {
		System.out.println("Server:received [" + msg + "]");
		ctx.channel().write("HI!");
		if("bye".equals(msg)){
			ChannelFuture f = ctx.write("Have a good day!");
			f.addListener(ChannelFutureListener.CLOSE);
		}
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("a client connected!");
		ctx.channel().write("hello!");
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("a client leaved!");
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		logger.error("exception", cause);
		ctx.close();
	}

}
