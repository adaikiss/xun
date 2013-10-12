/**
 * 
 */
package org.adaikiss.xun.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author hlw
 * 
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {
	private static final Logger logger = LoggerFactory
			.getLogger(ClientHandler.class.getName());


	public ClientHandler() {
		
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		ctx.write(msg);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}

}