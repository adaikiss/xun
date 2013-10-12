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

	private ServerSocketChannel ssc;
	private Pipe pipe;
//	private Scanner scanner;
	private SelectableChannel inChannel;
	private List<SocketChannel> clients;

	private ServerWindow win;

	public Server(ServerWindow win){
		this.win = win;
		clients = new LinkedList<SocketChannel>();
	}

	public void start(String host, int port) {
		try {
			selector = Selector.open();
			pipe = Pipe.open();
			inChannel = pipe.source();
			inChannel.configureBlocking(false);
			inChannel.register(selector, SelectionKey.OP_READ);
			ssc = ServerSocketChannel.open();
			ssc.configureBlocking(false);
			ssc.socket().bind(new InetSocketAddress(host, port));
			ssc.register(selector, SelectionKey.OP_ACCEPT);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
//		scanner = new Scanner(System.in, "UTF-8");
		
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
//		while (true) {
//			String msg = scanner.nextLine();
//			writeToClients(msg);
//		}
	}

	public void writeToClients(String msg){
		WritableByteChannel channel = pipe.sink();
		try {
			channel.write(EncodeUtil.StringToByteBuffer(msg));
		} catch (IOException e) {
			e.printStackTrace();
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
			win.write(msg);
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
		win.write("a new client connected!");
	}

	public void stop(){
		try {
			selector.close();
			selector = null;
			ssc.close();
			ssc = null;
			inChannel.close();
			inChannel = null;
			clients.clear();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
//		new Server().start("localhost", 12345);
	}

}
