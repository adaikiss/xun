/**
 * 
 */
package org.adaikiss.xun.mina2.chat;

import java.net.InetSocketAddress;
import java.util.Date;
import java.util.Scanner;

import org.adaikiss.xun.netty.chat.proto.MessageProto.Message;
import org.adaikiss.xun.netty.chat.proto.MessageProto.Message.MessageType;
import org.adaikiss.xun.netty.chat.proto.MessageProto.Message.User;
import org.apache.mina.core.RuntimeIoException;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.protobuf.ProtobufCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

/**
 * @author hlw
 * 
 */
public class ChatClient {
	IoSession session;
	private String name = "anonymous";

	public ChatClient(String host, int port) {
		IoConnector connector = new NioSocketConnector();
		connector.setConnectTimeoutMillis(30 * 1000);
		connector.getFilterChain().addLast("codec",
				new ProtocolCodecFilter(ProtobufCodecFactory.newInstance(Message.getDefaultInstance())));
		connector.getFilterChain().addLast("logger", new LoggingFilter());
		connector.setHandler(new ChatClientHandler());
		try {
			ConnectFuture future = connector.connect(new InetSocketAddress(
					host, port));
			future.awaitUninterruptibly();
			session = future.getSession();
			new Thread() {

				@Override
				public void run() {
					Scanner s = new Scanner(System.in);
					for (;;) {
						if (session.isClosing()) {
							return;
						}
						String str = s.nextLine();
						write(str);
					}
				}

			}.start();
		} catch (RuntimeIoException e) {
			System.err.println("Failed to connect.");
			e.printStackTrace();
		}
		session.getCloseFuture().awaitUninterruptibly();
		connector.dispose();
	}

	public void stop(){
		session.close(true);
		session = null;
	}

	public void write(String msg) {
		Message m = Message
				.newBuilder()
				.setType(MessageType.Chat)
				.setTime(new Date().getTime())
				.setContent(msg)
				.setUser(
						User.newBuilder().setName(getName())
								.setSystem(true)).build();
		session.write(m);
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new ChatClient("localhost", 8080);
	}

}
