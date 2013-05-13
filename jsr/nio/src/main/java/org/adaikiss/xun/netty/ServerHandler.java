/**
 * 
 */
package org.adaikiss.xun.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundMessageHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author hlw
 *
 */
@Sharable
public class ServerHandler extends ChannelInboundMessageHandlerAdapter<String> {

	private static final Logger logger = LoggerFactory.getLogger(ServerHandler.class);

	private static final ChannelGroup clients = new DefaultChannelGroup();

	static void sendToAll(String msg){
		for(Channel client : clients){
			client.write(msg + "\r\n");
		}
	}

	@Override
	public void messageReceived(ChannelHandlerContext ctx, String msg)
			throws Exception {
		for(Channel client : clients){
			if(client == ctx.channel()){
				client.write("[you]" + msg + "\r\n");
			}else{
				client.write("[" + ctx.channel().remoteAddress() + "]" + msg + "\r\n");
			}
		}
		System.err.println("[" + ctx.channel().remoteAddress() + "]" + msg + "\r\n");
		if("bye".equals(msg)){
			ctx.close();
		}
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("a client connected!");
		ctx.write("hello!" + "\r\n");
		clients.add(ctx.channel());
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("a client disconnected!");
		clients.remove(ctx.channel());
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		logger.error("exception", cause);
		ctx.close();
	}

}
