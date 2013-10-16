/**
 * 
 */
package org.adaikiss.xun.chat.oio;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.oio.OioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.oio.OioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.util.Date;
import java.util.Scanner;

import org.adaikiss.xun.netty.chat.proto.MessageProto.Message;
import org.adaikiss.xun.netty.chat.proto.MessageProto.Message.MessageType;
import org.adaikiss.xun.netty.chat.proto.MessageProto.Message.User;

/**
 * @author hlw
 * 
 */
public class ChatServer {
	ChannelFuture f;
	EventLoopGroup bossGroup;
	EventLoopGroup workerGroup;
	Channel channel;
	private ChatServerHandler handler;
	ChatServerWindow window;

	public ChatServer(int port, ChatServerWindow window) {
		this.window = window;
		bossGroup = new OioEventLoopGroup();// 处理accept事件
		workerGroup = new OioEventLoopGroup();// 处理accept到的channel
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup);
			b.option(ChannelOption.SO_BACKLOG, 100);
			b.channel(OioServerSocketChannel.class);
			handler = new ChatServerHandler(this);
			b.handler(new LoggingHandler(LogLevel.INFO)).childHandler(
					new ChannelInitializer<SocketChannel>() {

						@Override
						protected void initChannel(SocketChannel ch)
								throws Exception {
							ch.pipeline().addLast(new ProtobufDecoder(Message.getDefaultInstance()), new ProtobufEncoder(), handler);
						}
					});
			f = b.bind(port).sync();
			channel = f.channel();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void stop() {
		f.awaitUninterruptibly();
		channel.close();
		f = null;
		channel = null;
		bossGroup.shutdownGracefully();
		workerGroup.shutdownGracefully();
		bossGroup = null;
		workerGroup = null;
	}

	public void write(String msg){
		Message m = Message.newBuilder().setType(MessageType.Notice).setTime(new Date().getTime()).setContent(msg).setUser(User.newBuilder().setName("system").setSystem(true)).build();
		handler.writeToAll(m);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ChatServer server = new ChatServer(8080, null);
		Scanner s = new Scanner(System.in);
		String str = null;
		for(;;){
			str = s.nextLine();
			if("quit".equals(str)){
				server.stop();
				break;
			}
			server.write(str);
		}
	}

}
