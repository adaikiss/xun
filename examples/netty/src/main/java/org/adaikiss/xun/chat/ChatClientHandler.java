/**
 * 
 */
package org.adaikiss.xun.chat;

import java.util.Date;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import org.adaikiss.xun.netty.chat.proto.MessageProto.Message;
import org.adaikiss.xun.netty.chat.proto.MessageProto.Message.MessageType;
import org.adaikiss.xun.netty.chat.proto.MessageProto.Message.User;

/**
 * @author hlw
 *
 */
public class ChatClientHandler extends SimpleChannelInboundHandler<Message>{

	private ChatClient client;
	public ChatClientHandler(ChatClient client){
		this.client = client;
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		Message m = Message.newBuilder().setType(MessageType.Login).setTime(new Date().getTime()).setUser(User.newBuilder().setName(client.window.getName()).setPassword("123456").setSystem(false)).build();
		ctx.writeAndFlush(m);
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		Message m = Message.newBuilder().setType(MessageType.Logout).setTime(new Date().getTime()).setUser(User.newBuilder().setName(client.window.getName()).setSystem(false)).build();
		ctx.writeAndFlush(m);
	}

	@Override
	public void channelRead0(ChannelHandlerContext ctx, Message msg)
			throws Exception {
		switch(msg.getType()){
		case Chat:
		case Notice:
			client.window.showMsg(msg.getUser().getName() + ":" + msg.getContent());
			break;
		case Login:
			client.window.showMsg(msg.getUser().getName() + " joined!");
			break;
		case Logout:
			client.window.showMsg(msg.getUser().getName() + " left!");
			break;
		}
		System.err.println(msg.getUser().getName() + ":" + msg.getContent());
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		super.channelReadComplete(ctx);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

}
