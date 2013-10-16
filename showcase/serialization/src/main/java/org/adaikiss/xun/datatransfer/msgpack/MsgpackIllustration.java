/**
 * 
 */
package org.adaikiss.xun.datatransfer.msgpack;

import org.msgpack.MessagePack;

/**
 * @author hlw
 * 
 */
public class MsgpackIllustration {
	static MessagePack pack = new MessagePack();

	public static byte[] serialize(String userName, boolean system,
			String password, long time, String content, int messageType)
			throws Exception {
		Message msg = new Message(time, content, MessageType.get(messageType),
				new User(userName, system, password));
		// MessagePack pack = new MessagePack();
		return pack.write(msg);
	}

	public static byte[] serialize1(Message msg) throws Exception{
		return pack.write(msg);
	}

	public static void deserialize(byte[] bytes) throws Exception {
		// MessagePack pack = new MessagePack();
		pack.read(bytes, Message.class);
	}
}
