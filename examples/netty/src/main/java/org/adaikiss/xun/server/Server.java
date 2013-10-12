/**
 * 
 */
package org.adaikiss.xun.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author hlw
 * 
 */
public class Server {
	public static void main(String[] args) throws Exception {
		// Configure the server.
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		// Configure the server.
		ServerBootstrap bootstrap;
		try {
			bootstrap = new ServerBootstrap();
			bootstrap.group(bossGroup, workerGroup)
					.channel(NioServerSocketChannel.class)
					.option(ChannelOption.SO_BACKLOG, 100)
					.handler(new LoggingHandler(LogLevel.INFO))
					.childHandler(new ChannelInitializer<SocketChannel>() {
						@Override
						public void initChannel(SocketChannel ch) throws Exception {
							ch.pipeline().addLast(new ServerHandler());
						}
					});
			 // Start the server.  
            ChannelFuture f = bootstrap.bind(8080).sync();  
  
            // Wait until the server socket is closed.  
            f.channel().closeFuture().sync();  
        } finally {  
            // Shut down all event loops to terminate all threads.  
            bossGroup.shutdownGracefully();  
            workerGroup.shutdownGracefully();  
        }  
	}
}