/**
 * 
 */
package org.adaikiss.xun.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author hlw
 * 
 */
public class Client {

	private static final Logger logger = LoggerFactory.getLogger(Client.class);

	private String host;
	private int port;
	private Channel channel;
	private EventLoopGroup group;

	private static final ClientHandler CLIENTHANDLER = new ClientHandler();
	private static final StringDecoder DECODER = new StringDecoder(
			CharsetUtil.UTF_8);
	private static final StringEncoder ENCODER = new StringEncoder(
			CharsetUtil.UTF_8);

	private Client(String host, int port) {
		this.host = host;
		this.port = port;
	}

	private void start() throws Exception {
		group = new NioEventLoopGroup();
		Bootstrap b = new Bootstrap();
		b.group(group).channel(NioSocketChannel.class)
				.option(ChannelOption.TCP_NODELAY, true)
				.handler(new ChannelInitializer<SocketChannel>() {

					@Override
					protected void initChannel(SocketChannel ch)
							throws Exception {
						ChannelPipeline pipeline = ch.pipeline();

						// Add the text line codec combination first,
						pipeline.addLast(
								"framer",
								new DelimiterBasedFrameDecoder(8192, Delimiters
										.lineDelimiter()));
						pipeline.addLast("decoder", DECODER);
						pipeline.addLast("encoder", ENCODER);

						// and then business logic.
						pipeline.addLast("handler", CLIENTHANDLER);
					}

				});
		channel = b.connect(new InetSocketAddress(host, port)).sync().channel();
		Scanner scanner = new Scanner(System.in);
		ChannelFuture f = null;
		while (true) {
			String input = scanner.nextLine();
			f = channel.write(input + "\r\n");
			if ("bye".equals(input)) {
				channel.closeFuture().sync();
				break;
			}
			if (f != null) {
				f.sync();
			}
		}
		group.shutdownGracefully();
		// new Thread() {
		// @Override
		// public void run() {
		// Scanner scanner = new Scanner(System.in);
		// ChannelFuture f = null;
		// try {
		// while (true) {
		// if (channel == null) {
		// Thread.sleep(1000);
		// continue;
		// }
		// String input = scanner.nextLine();
		// f = channel.write(input);
		// if ("bye".equals(input)) {
		// channel.closeFuture().sync();
		// break;
		// }
		// if(f != null){
		// f.sync();
		// }
		// }
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		// group.shutdown();
		// }
		// }.start();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		new Client("localhost", 12345).start();
//		Socket fromserver = new Socket("localhost", 12345);
//
//        PrintWriter out = new PrintWriter(fromserver.getOutputStream(), true);
//
//        out.println("hello!\r\n");
//
//        BufferedReader in = new BufferedReader(new InputStreamReader(fromserver.getInputStream()));
//        String line = null;
//        while((line = in.readLine()) != null ){
//        	System.out.println(line);
//        }
//        out.close();
//        fromserver.close();
	}

}
