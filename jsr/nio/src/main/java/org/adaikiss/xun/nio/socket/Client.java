/**
 * 
 */
package org.adaikiss.xun.nio.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.Pipe;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.channels.WritableByteChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import org.adaikiss.xun.utils.EncodeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author hlw
 * 
 */
public class Client {

	private static final Logger logger = LoggerFactory.getLogger(Client.class);
	private ByteBuffer buffer = ByteBuffer.allocate(8);
	private SocketChannel socketChannel;
	private SelectableChannel inChannel;
	private Selector selector;
	private Scanner scanner;
	private Pipe pipe;
	private SocketAddress addr;

	private void start() throws IOException {
		pipe = Pipe.open();
		inChannel = pipe.source();
		inChannel.configureBlocking(false);
		selector = Selector.open();
		inChannel.register(selector, SelectionKey.OP_READ);
		addr = new InetSocketAddress("localhost", 12345);
		scanner = new Scanner(System.in, "UTF-8");
		new Thread() {
			@Override
			public void run() {
				try {
					connect();
					while (selector.select() > 0) {
						Set<SelectionKey> selectionKeys = selector
								.selectedKeys();
						Iterator<SelectionKey> selectionKeysIterator = selectionKeys
								.iterator();
						while (selectionKeysIterator.hasNext()) {
							SelectionKey selectionKey = selectionKeysIterator
									.next();
							selectionKeysIterator.remove();
							SelectableChannel activeChannel = selectionKey
									.channel();
							if (activeChannel.equals(inChannel)) { // ============================
																	// input
																	// activity
								ByteBuffer handler = ByteBuffer.allocate(1024);
								((ReadableByteChannel) selectionKey.channel())
										.read(handler);
								handler.flip();
								socketChannel.write(handler);
							} else if (activeChannel.equals(socketChannel)) { // ====================
																				// server
																				// activity
								SocketChannel channel = (SocketChannel) activeChannel;
								if (selectionKey.isValid()
										&& selectionKey.isConnectable()) {
									if (channel.isConnectionPending()) {
										try {
											socketChannel
													.finishConnect();
										} catch (IOException ex) {
											reconnect(socketChannel);
											break;
										}
									}
									channel.register(selector,
											SelectionKey.OP_READ);
									System.out
											.println("Client:server connected!");
									selectionKey
											.interestOps(SelectionKey.OP_READ);
									break;
								}
								if (selectionKey.isValid()
										&& selectionKey.isReadable()) {
									buffer.clear();
									channel.read(buffer);
									buffer.flip();
									System.out.println("Server:"
											+ EncodeUtil.ByteBufferToString(buffer));
									buffer.flip();
									break;
								}
								if (selectionKey.isValid()
										&& selectionKey.isWritable()) {
									throw new UnsupportedOperationException();
								}
							}
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}.start();
		while (true) {
			if (socketChannel != null && socketChannel.isConnected()) {
				System.out.println("Client:please input message:");
				String msg = scanner.nextLine();
				System.out.println(msg);
				sendMessage(msg);
			}
		}
	}

	private void reconnect (SocketChannel lostChannel) {
		try {
			lostChannel.close();
		} catch (IOException ex1) {
			logger.error("Error closing channel");
		}
		connect();
	}

	private void connect(){
		try {
			socketChannel = SocketChannel.open();
			socketChannel.configureBlocking(false);
			socketChannel.connect(addr);
		} catch (IOException e) {
			logger.error("Error opening socketChannel!", e);
		}
		try {
			socketChannel.register(selector, SelectionKey.OP_CONNECT);
		} catch (ClosedChannelException e) {
			e.printStackTrace();
			logger.error("Error regestering channels!", e);
		}
	}

	public void sendMessage(String str) {
		WritableByteChannel channel = pipe.sink();
		ByteBuffer buf = EncodeUtil.StringToByteBuffer(str);
		try {
			if (buf != null) {
				channel.write(buf);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		new Client().start();
	}

}
