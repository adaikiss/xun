/**
 * 
 */
package org.adaikiss.xun.nio.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.WritableByteChannel;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.adaikiss.xun.utils.EncodeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author hlw
 * 
 */
public class Server {

	private static final Logger logger = LoggerFactory.getLogger(Server.class);

	private Selector selector;

	private ByteBuffer buffer = ByteBuffer.allocate(1024);

	private Pipe pipe;
	private Scanner scanner;
	private SelectableChannel inChannel;
	private List<SocketChannel> clients;

	private void start(String host, int port) throws IOException {
		ServerSocketChannel ssc = ServerSocketChannel.open();
		ssc.configureBlocking(false);
		ssc.socket().bind(new InetSocketAddress(host, port));
		selector = Selector.open();
		ssc.register(selector, SelectionKey.OP_ACCEPT);
		pipe = Pipe.open();
		inChannel = pipe.source();
		inChannel.configureBlocking(false);
		inChannel.register(selector, SelectionKey.OP_READ);
		scanner = new Scanner(System.in, "UTF-8");
		clients = new LinkedList<SocketChannel>();
		new Thread() {
			@Override
			public void run() {

				try {
					while (selector.select() > 0) {
						Set<SelectionKey> selectionKeys = selector
								.selectedKeys();
						Iterator<SelectionKey> selectionKeysIterator = selectionKeys
								.iterator();
						while (selectionKeysIterator.hasNext()) {
							SelectionKey selectionKey = selectionKeysIterator
									.next();
							selectionKeysIterator.remove();
							if (selectionKey.isValid()) {
								processKey(selectionKey);
							} else {
								logger.debug("connection is not available!");
							}
						}
					}
				} catch (IOException e) {
					logger.error("", e);
				}
			}
		}.start();
		while (true) {
			String msg = scanner.nextLine();
			WritableByteChannel channel = pipe.sink();
			channel.write(EncodeUtil.StringToByteBuffer(msg));
		}
	}

	private void processKey(SelectionKey selectionKey) {
		try {
			if (inChannel.equals(selectionKey.channel())) {
				buffer.clear();
				((ReadableByteChannel) selectionKey.channel()).read(buffer);
				buffer.flip();
				writeToAllClient(buffer);
			} else {
				if (selectionKey.isAcceptable()) {
					accept(selectionKey);
					return;
				}
				if (selectionKey.isReadable()) {
					read(selectionKey);
					return;
				}
			}
		} catch (IOException e) {
			logger.warn("", e);
		}
	}

	private void writeToAllClient(ByteBuffer buffer) {
		for (SocketChannel channel : clients) {
			try {
				channel.write(buffer);
				buffer.flip();
			} catch (IOException e) {
				logger.error("Error writing to channel!", e);
			}
		}
	}

	private void read(SelectionKey selectionKey) throws IOException {
		SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
		buffer.clear();
		int count = 0;
		try {
			count = socketChannel.read(buffer);
			buffer.flip();
		} catch (IOException e) {
			selectionKey.cancel();
			socketChannel.close();
			return;
		}
		if (count > 0) {
			String msg = EncodeUtil.ByteBufferToString(buffer);
			System.out.println("Server:received [" + msg + "]");
		}
	}

	private void accept(SelectionKey selectionKey) throws IOException {
		ServerSocketChannel ssc = (ServerSocketChannel) selectionKey.channel();
		SocketChannel clientChannel = ssc.accept();
		clients.add(clientChannel);
		clientChannel.configureBlocking(false);
		clientChannel.register(selector, SelectionKey.OP_READ);
		ByteBuffer buffer = EncodeUtil.StringToByteBuffer("welcome!");
		clientChannel.write(buffer);
		buffer.clear();
		System.out.println("Server:a new client connected!");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		new Server().start("localhost", 12345);
	}

}
