/**
 * 
 */
package org.adaikiss.xun.client;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

/**
 * @author hlw
 * 
 */
public class Client {
	private static final String HOST = "localhost";
	private static final int PORT = 8080;
	public static void main(String[] args) throws Exception {

		// Configure the client.
		ClientBootstrap bootstrap = new ClientBootstrap(
				new NioClientSocketChannelFactory(
						Executors.newCachedThreadPool(),
						Executors.newCachedThreadPool()));

		ClientHandler handler =  new ClientHandler("Hello, world!");
		bootstrap.getPipeline().addLast("handler", handler);
		// Start the connection attempt.
		ChannelFuture future = bootstrap.connect(new InetSocketAddress(HOST,
				PORT));

		// Wait until the connection is closed or the connection attempt fails.
		future.getChannel().getCloseFuture().awaitUninterruptibly();

		// Shut down thread pools to exit.
		bootstrap.releaseExternalResources();
	}
}
