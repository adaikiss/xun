package org.adaikiss.xun.mina2.chat;

import java.net.InetSocketAddress;
import java.util.Date;
import java.util.Scanner;

import org.adaikiss.xun.netty.chat.proto.MessageProto.Message;
import org.adaikiss.xun.netty.chat.proto.MessageProto.Message.MessageType;
import org.adaikiss.xun.netty.chat.proto.MessageProto.Message.User;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.protobuf.ProtobufCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

public class ChatServer {
	private IoAcceptor acceptor;
	public ChatServer(int port) throws Exception{
		acceptor = new NioSocketAcceptor();
		acceptor.getFilterChain().addLast("logger", new LoggingFilter());
		acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(ProtobufCodecFactory.newInstance(Message.getDefaultInstance())));
		acceptor.setHandler(new ChatServerHandler());
		acceptor.getSessionConfig().setReadBufferSize(2048);
		acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
		acceptor.bind(new InetSocketAddress(port));
		Scanner s = new Scanner(System.in);
		String str = null;
		for(;;){
			str = s.nextLine();
			if("quit".equals(str)){
				System.exit(0);
				return;
			}
			write(str);
		}
	}

	public void stop(){
		acceptor.dispose(true);
		acceptor = null;
	}

	public void write(String msg){
		Message m = Message.newBuilder().setType(MessageType.Notice).setTime(new Date().getTime()).setContent(msg).setUser(User.newBuilder().setName("system").setSystem(true)).build();
		acceptor.broadcast(m);
	}

	public static void main(String[] args) throws Exception{
		new ChatServer(8080);
	}
}
