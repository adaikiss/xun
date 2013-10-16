/**
 * 
 */
package org.adaikiss.xun;

import java.io.ByteArrayOutputStream;
import java.util.Date;

import org.adaikiss.xun.netty.chat.proto.MessageProto.Message;
import org.adaikiss.xun.netty.chat.proto.MessageProto.Message.MessageType;
import org.adaikiss.xun.netty.chat.proto.MessageProto.Message.User;
import org.junit.Assert;
import org.junit.Test;

import com.google.protobuf.ByteString;

/**
 * @author HuLingwei
 *
 */
public class ProtoTest {

	@Test
	public void testProto() throws Exception{
		Message.Builder builder = Message.newBuilder();
		builder.setType(MessageType.Login).setTime(new Date().getTime()).setUser(User.newBuilder().setName("netty").setPassword("123456").setSystem(false));
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		builder.build().writeTo(output);
		byte[] msg = output.toByteArray();
		Message message = Message.parseFrom(ByteString.copyFrom(msg));
		Assert.assertEquals("netty", message.getUser().getName());
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		Message.Builder builder = Message.newBuilder();
		builder.setType(MessageType.Login).setTime(new Date().getTime()).setUser(User.newBuilder().setName("netty").setPassword("123456").setSystem(false));
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		builder.build().writeTo(output);
		byte[] msg = output.toByteArray();
		Message message = Message.parseFrom(ByteString.copyFrom(msg));
		System.out.println(message);
	}

}
