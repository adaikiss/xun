/**
 * 
 */
package org.adaikiss.xun.nio.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author hlw
 *
 */
public class Server {

	private Selector selector;

	private ByteBuffer buffer = ByteBuffer.allocate(8);

	private void start(String host, int port) throws IOException{
		ServerSocketChannel ssc = ServerSocketChannel.open();
		ssc.configureBlocking(false);
		ssc.bind(new InetSocketAddress(host, port));
		selector = Selector.open();
		ssc.register(selector, SelectionKey.OP_ACCEPT);
		while(!Thread.currentThread().isInterrupted()){
			selector.select();
			Set<SelectionKey> selectionKeys = selector.selectedKeys();
			Iterator<SelectionKey> selectionKeysIterator = selectionKeys.iterator();
			while(selectionKeysIterator.hasNext()){
				SelectionKey selectionKey = selectionKeysIterator.next();
				if(!selectionKey.isValid()){
					continue;
				}
				if(selectionKey.isReadable()){
					read(selectionKey);
					selectionKeysIterator.remove();
					continue;
				}
				if(selectionKey.isAcceptable()){
					accept(selectionKey);
					selectionKeysIterator.remove();
					continue;
				}
			}
		}
	}

	private void read(SelectionKey selectionKey) throws IOException{
		SocketChannel socketChannel = (SocketChannel)selectionKey.channel();
		buffer.clear();
		int count = 0;
		try {
			count = socketChannel.read(buffer);
		} catch (IOException e) {
			selectionKey.cancel();
			socketChannel.close();
			return;
		}
		if(count > 0){
			String msg = new String(buffer.array(), 0, count);
			System.out.println("Server: received [" + msg + "]");
			socketChannel.write(ByteBuffer.wrap(("server sent " + msg).getBytes()));
		}
	}

	private void accept(SelectionKey selectionKey) throws IOException{
		ServerSocketChannel ssc = (ServerSocketChannel)selectionKey.channel();
		SocketChannel clientChannel = ssc.accept();
		clientChannel.configureBlocking(false);
		clientChannel.register(selector, SelectionKey.OP_READ);
		clientChannel.register(selector, SelectionKey.OP_WRITE);
		System.out.println("Server:a new client connected!");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException{
		new Server().start("localhost", 12345);
	}

}
