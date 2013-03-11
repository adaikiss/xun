/**
 * 
 */
package org.adaikiss.xun.nio.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * @author hlw
 *
 */
public class Client {

	private ByteBuffer serverBuffer = ByteBuffer.allocate(8);

	private void start() throws IOException{
		SocketChannel socketChannel = SocketChannel.open();
		socketChannel.configureBlocking(false);
		socketChannel.connect(new InetSocketAddress("localhost", 12345));
		Selector selector = Selector.open();
		socketChannel.register(selector, SelectionKey.OP_CONNECT);
		Scanner scanner = new Scanner(System.in);
		while(true){
			selector.select();
			Set<SelectionKey> selectionKeys = selector.selectedKeys();
			Iterator<SelectionKey> selectionKeysIterator = selectionKeys.iterator();
			while(selectionKeysIterator.hasNext()){
				SelectionKey selectionKey = selectionKeysIterator.next();
				selectionKeysIterator.remove();
				if(selectionKey.isConnectable()){
					socketChannel.finishConnect();
					socketChannel.register(selector, SelectionKey.OP_WRITE);
					System.out.println("server connected!");
					break;
				}
				if(selectionKey.isWritable()){
					System.out.println("please input message:");
					String msg = scanner.nextLine();
					ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
					socketChannel.write(buffer);
				}
				if(selectionKey.isReadable()){
					serverBuffer.clear();
					socketChannel.read(serverBuffer);
					System.out.println("server:" + new String(serverBuffer.array()));
				}
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException{
		new Client().start();
	}

}
