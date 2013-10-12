/**
 * 
 */
package org.adaikiss.xun.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;

import java.net.InetSocketAddress;

/**
 * @author hlw
 * 
 */
public class Client {
	private static final String HOST = "localhost";
	private static final int PORT = 8080;

	public static void main(String[] args) throws Exception {

		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap bootstrap = new Bootstrap();
			bootstrap.group(group);
			bootstrap.handler(new ChannelInitializer<Channel>() {
				public void initChannel(Channel channel) {
					channel.pipeline().addLast(new ClientHandler());
				}
			});
			bootstrap.option(ChannelOption.TCP_NODELAY, true).option(
					ChannelOption.SO_KEEPALIVE, true);
			ChannelFuture f = bootstrap.connect(
					new InetSocketAddress(HOST, PORT)).sync();
			// Wait until the connection is closed.
			f.channel().closeFuture().sync();
		} finally {
			// Shut down the event loop to terminate all threads.
			group.shutdownGracefully();
		}
	}
}
