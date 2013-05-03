/**
 * 
 */
package org.adaikiss.xun.nio.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
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
	private SocketChannel socketChannel;
	private Selector selector;
	private Scanner scanner;

	private void start() throws IOException {
		socketChannel = SocketChannel.open();
		socketChannel.configureBlocking(false);
		socketChannel.connect(new InetSocketAddress("localhost", 12345));
		selector = Selector.open();
		socketChannel.register(selector, SelectionKey.OP_CONNECT);
		scanner = new Scanner(System.in);
		new Thread() {
			@Override
			public void run() {
				while (true) {
					try {
						selector.select();
						Set<SelectionKey> selectionKeys = selector.selectedKeys();
						Iterator<SelectionKey> selectionKeysIterator = selectionKeys
								.iterator();
						while (selectionKeysIterator.hasNext()) {
							SelectionKey selectionKey = selectionKeysIterator
									.next();
							selectionKeysIterator.remove();
							if (selectionKey.isConnectable()) {
								socketChannel.finishConnect();
								socketChannel.register(selector,
										SelectionKey.OP_WRITE);
								socketChannel.register(selector,
										SelectionKey.OP_READ);
								System.out.println("Client:server connected!");
								break;
							}
							if (selectionKey.isReadable()) {
								serverBuffer.clear();
								socketChannel.read(serverBuffer);
								System.out.println("Client:"
										+ new String(serverBuffer.array()));
							}
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
		while(true){
			if (socketChannel.isConnected()) {
				System.out.println("Client:please input message:");
				String msg = scanner.nextLine();
				ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
				socketChannel.write(buffer);
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		new Client().start();
	}

}
