/**
 * 
 */
package org.adaikiss.xun.nio.socket;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 * @author hlw
 * 
 */
public class TimeQuery {

	private static Charset charset = Charset.forName("US-ASCII");
	private static CharsetDecoder decoder = charset.newDecoder();

	private static void query(String host, int port) throws Exception {
		SocketChannel socketChannel = null;
		try {
			socketChannel = SocketChannel.open();
			ByteBuffer buff = ByteBuffer.allocate(1024);
			InetSocketAddress inetSocketAddress = new InetSocketAddress(host,
					port);
			socketChannel.connect(inetSocketAddress);
			socketChannel.read(buff);
			buff.flip();
			CharBuffer cb = decoder.decode(buff);
			System.out.print(inetSocketAddress + " : " + cb);
		} finally {
			if (socketChannel != null)
				socketChannel.close();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		ExecutorService executor = Executors.newFixedThreadPool(5);
//		for(int i = 0;i<20;i++){
//			executor.execute(new Runner("localhost", 12345));
//		}
//		executor.shutdownNow();
		for(int i = 0;i<20;i++){
			try {
				query("localhost", 12345);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

//	static class Runner implements Runnable{
//		private String host;
//		private int port;
//		public Runner(String host, int port){
//			this.host = host;
//			this.port = port;
//		}
//		@Override
//		public void run() {
//			try {
//				query(host, port);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		
//	}
}
