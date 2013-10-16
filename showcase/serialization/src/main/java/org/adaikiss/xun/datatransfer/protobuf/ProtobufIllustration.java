/**
 * 
 */
package org.adaikiss.xun.datatransfer.protobuf;

import org.adaikiss.xun.netty.chat.proto.MessageProto.Message;
import org.adaikiss.xun.netty.chat.proto.MessageProto.Message.MessageType;
import org.adaikiss.xun.netty.chat.proto.MessageProto.Message.User;

/**
 * @author hlw
 * 
 */
public class ProtobufIllustration {
	public static byte[] serialize(String userName, boolean system,
			String password, long time, String content, int messageType)
			throws Exception {
		Message msg = Message
				.newBuilder()
				.setTime(time)
				.setContent(content)
				.setType(MessageType.valueOf(messageType))
				.setUser(
						User.newBuilder().setName(userName).setSystem(system)
								.build()).build();
		return msg.toByteArray();
	}

	public static byte[] serialize1(Message msg) {
		return msg.toByteArray();
	}

	public static void deserialize(byte[] bytes) throws Exception {
		Message.parseFrom(bytes);
	}
}
