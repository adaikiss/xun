/**
 * 
 */
package org.adaikiss.xun.mina2.chat;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.Scanner;

import org.apache.mina.core.RuntimeIoException;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

/**
 * @author hlw
 * 
 */
public class ChatClient {
	IoSession session;

	public ChatClient(String host, int port) {
		IoConnector connector = new NioSocketConnector();
		connector.setConnectTimeoutMillis(30 * 1000);
		connector.getFilterChain().addLast("codec",
				new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));
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
						session.write(str);
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

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new ChatClient("localhost", 8080);
	}

}
