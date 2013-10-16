/**
 * 
 */
package org.adaikiss.xun.chat.nio;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;

import java.util.Date;
import java.util.Scanner;

import org.adaikiss.xun.netty.chat.proto.MessageProto.Message;
import org.adaikiss.xun.netty.chat.proto.MessageProto.Message.MessageType;
import org.adaikiss.xun.netty.chat.proto.MessageProto.Message.User;

/**
 * @author hlw
 * 
 */
public class ChatClient {

	private EventLoopGroup group;
	private ChannelFuture f;
	private Channel channel;
	ChatClientWindow window;

	public ChatClient(String host, int port, ChatClientWindow window) {
		this.window = window;
		group = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(group);
			b.channel(NioSocketChannel.class);
			b.option(ChannelOption.TCP_NODELAY, true);
			b.handler(new ChannelInitializer<SocketChannel>() {

				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(
							new ProtobufDecoder(Message.getDefaultInstance()),
							new ProtobufEncoder(),
							new ChatClientHandler(ChatClient.this));
				}

			});
			f = b.connect(host, port).sync();
			channel = f.channel();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void stop() {
		f.awaitUninterruptibly();
		channel.close();
		group.shutdownGracefully();
		f = null;
		channel = null;
		group = null;
	}

	public void write(String msg) {
		Message m = Message
				.newBuilder()
				.setType(MessageType.Chat)
				.setTime(new Date().getTime())
				.setContent(msg)
				.setUser(
						User.newBuilder().setName(window.getName())
								.setSystem(true)).build();
		channel.writeAndFlush(m);
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ChatClient client = new ChatClient("localhost", 8080, null);
		Scanner s = new Scanner(System.in);
		String str = null;
		for (;;) {
			str = s.nextLine();
			if ("quit".equals(str)) {
				client.stop();
				break;
			}
			client.write(str);
		}
	}

}
