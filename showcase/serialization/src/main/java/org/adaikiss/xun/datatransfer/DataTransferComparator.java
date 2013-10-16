/**
 * 
 */
package org.adaikiss.xun.datatransfer;

import org.adaikiss.xun.datatransfer.msgpack.MsgpackIllustration;
import org.adaikiss.xun.datatransfer.protobuf.ProtobufIllustration;

/**
 * @author hlw
 * 
 */
public class DataTransferComparator {

	public static void run() throws Exception{
		long begin, cost;
		//start serialize
		begin = System.nanoTime();
		byte[] msgpackBytes = MsgpackIllustration.serialize("Jetty", false, null, 1381828132203l, "your sister!", 2);
		cost = System.nanoTime() - begin;
		System.out.println("msgpack  serialize costs:" + cost + "ns, bytes length:" + msgpackBytes.length);

		begin = System.nanoTime();
		byte[] protobufBytes = ProtobufIllustration.serialize("Jetty", false, null, 1381828132203l, "your sister!", 2);
		cost = System.nanoTime() - begin;
		System.out.println("protobuf serialize costs:" + cost + "ns, bytes length:" + protobufBytes.length);

		//start deserialize
		begin = System.nanoTime();
		MsgpackIllustration.deserialize(msgpackBytes);
		cost = System.nanoTime() - begin;
		System.out.println("msgpack  deserialize costs:" + cost + "ns");

		begin = System.nanoTime();
		ProtobufIllustration.deserialize(protobufBytes);
		cost = System.nanoTime() - begin;
		System.out.println("protobuf deserialize costs:" + cost + "ns");
	}

	public static void run1(org.adaikiss.xun.netty.chat.proto.MessageProto.Message msg1, org.adaikiss.xun.datatransfer.msgpack.Message msg2) throws Exception{
		long begin, cost;
		//start serialize
		begin = System.nanoTime();
		byte[] msgpackBytes = MsgpackIllustration.serialize1(msg2);
		cost = System.nanoTime() - begin;
		System.out.println("msgpack  serialize costs:" + cost + "ns, bytes length:" + msgpackBytes.length);

		begin = System.nanoTime();
		byte[] protobufBytes = ProtobufIllustration.serialize1(msg1);
		cost = System.nanoTime() - begin;
		System.out.println("protobuf serialize costs:" + cost + "ns, bytes length:" + protobufBytes.length);

		//start deserialize
		begin = System.nanoTime();
		MsgpackIllustration.deserialize(msgpackBytes);
		cost = System.nanoTime() - begin;
		System.out.println("msgpack  deserialize costs:" + cost + "ns");

		begin = System.nanoTime();
		ProtobufIllustration.deserialize(protobufBytes);
		cost = System.nanoTime() - begin;
		System.out.println("protobuf deserialize costs:" + cost + "ns");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		MsgpackIllustration.deserialize(MsgpackIllustration.serialize("Jetty", false, null, 1381828132203l, "your sister!", 2));
		ProtobufIllustration.deserialize(ProtobufIllustration.serialize("Jetty", false, null, 1381828132203l, "your sister!", 2));
		org.adaikiss.xun.netty.chat.proto.MessageProto.Message msg1 = org.adaikiss.xun.netty.chat.proto.MessageProto.Message.newBuilder().setTime(1381828132203l).setContent("your sister!").setType(org.adaikiss.xun.netty.chat.proto.MessageProto.Message.MessageType.valueOf(2)).setUser(org.adaikiss.xun.netty.chat.proto.MessageProto.Message.User.newBuilder().setName("Jetty").setSystem(false).build()).build();
		org.adaikiss.xun.datatransfer.msgpack.Message msg2 = new org.adaikiss.xun.datatransfer.msgpack.Message(1381828132203l, "your sister!", org.adaikiss.xun.datatransfer.msgpack.MessageType.get(2),
				new org.adaikiss.xun.datatransfer.msgpack.User("Jetty", false, null));
		for(int i = 0;i<5;i++){
			run();
		}
		for(int i = 0;i<5;i++){
			run1(msg1, msg2);
		}
	}

}
