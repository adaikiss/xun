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
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author hlw
 *
 */
public class Server {

	private Selector selector;

	private ByteBuffer buffer = ByteBuffer.allocate(8);

	private Map<SocketChannel, byte[]> clients = new ConcurrentHashMap<SocketChannel, byte[]>();

	private void start() throws IOException{
		ServerSocketChannel ssc = ServerSocketChannel.open();
		ssc.configureBlocking(false);
		ssc.bind(new InetSocketAddress("localhost", 12345));
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
		byte[] bytes = clients.get(socketChannel);
		if(bytes == null){
			bytes = new byte[0];
		}
		if(count > 0){
			byte[] newBytes = new byte[bytes.length + count]; 
			System.arraycopy(bytes, 0, newBytes, 0, bytes.length); 
			System.arraycopy(buffer.array(), 0, newBytes, bytes.length, count);
			clients.put(socketChannel, newBytes);
			System.out.println("client:" + new String(newBytes));
			socketChannel.write(ByteBuffer.wrap("ƒ„±Ì√√∑Ú!".getBytes()));
		}
	}

	private void accept(SelectionKey selectionKey) throws IOException{
		ServerSocketChannel ssc = (ServerSocketChannel)selectionKey.channel();
		SocketChannel clientChannel = ssc.accept();
		clientChannel.configureBlocking(false);
		clientChannel.register(selector, SelectionKey.OP_READ);
		System.out.println("a new client connected!");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException{
		new Server().start();
	}

}
