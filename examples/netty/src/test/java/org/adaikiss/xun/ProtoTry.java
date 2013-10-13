/**
 * 
 */
package org.adaikiss.xun;

import java.io.ByteArrayOutputStream;
import java.util.Date;

import org.adaikiss.xun.netty.chat.proto.MessageProto.Message;
import org.adaikiss.xun.netty.chat.proto.MessageProto.Message.MessageType;
import org.adaikiss.xun.netty.chat.proto.MessageProto.Message.User;

import com.google.protobuf.ByteString;

/**
 * @author HuLingwei
 *
 */
public class ProtoTry {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		Message.Builder builder = Message.newBuilder();
		builder.setType(MessageType.Login).setTime(new Date().getTime()).setUser(User.newBuilder().setName("netty").setPassword("123456").setIp("localhost").setSystem(false));
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		builder.build().writeTo(output);
		String msg = output.toString();
		Message message = Message.parseFrom(ByteString.copyFromUtf8(msg));
		System.out.println(message);
	}

}
