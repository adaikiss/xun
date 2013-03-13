/**
 * 
 */
package org.adaikiss.xun.aio.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author hlw
 * 
 */
public class Server {

	private static final Logger logger = LoggerFactory.getLogger(Server.class);

	private AsynchronousServerSocketChannel server;

	private void start(String host, int port) throws IOException {
		AsynchronousChannelGroup group = AsynchronousChannelGroup
				.withThreadPool(Executors.newFixedThreadPool(10));
		server = AsynchronousServerSocketChannel.open(group);
		server.setOption(StandardSocketOptions.SO_REUSEADDR, true);
		server.setOption(StandardSocketOptions.SO_RCVBUF, 16 * 1024);
		server.bind(new InetSocketAddress(host, port), 100);
		server.accept(
				server,
				new CompletionHandler<AsynchronousSocketChannel, AsynchronousServerSocketChannel>() {
					public void completed(AsynchronousSocketChannel client,
							AsynchronousServerSocketChannel server) {
						logger.debug("Accepted a connection");

						// accept the next connection
						server.accept(server, this);

						// handle this connection
						final ByteBuffer buffer = ByteBuffer.allocate(1024);
						Attachment attachment = new Attachment(buffer, client);
						client.read(buffer, attachment,
								new CompletionHandler<Integer, Attachment>() {

									@Override
									public void completed(Integer result,
											Attachment attachment) {
										if (result > 0) {
											AsynchronousSocketChannel client = attachment
													.getClient();
											String msg = new String(attachment
													.getBuffer().array(), 0,
													result);
											attachment.getBuffer().clear();
											client.read(attachment.getBuffer(),
													attachment, this);
											System.out.println("Server:received[" + msg + "]");
											try {
												client.write(
														ByteBuffer
																.wrap(("you sent " + msg)
																		.getBytes()))
														.get();
											} catch (Exception e) {
												logger.error(
														"error writing messages!",
														e);
											}
										}
									}

									@Override
									public void failed(Throwable exc,
											Attachment attachment) {
										logger.error("error reading messages!",
												exc);
									}

								});
					}

					public void failed(Throwable exc,
							AsynchronousServerSocketChannel server) {
						logger.error("Failed to accept connection");
					}
				});
		try {
			// wait until group.shutdown()/shutdownNow(), or the thread is
			// interrupted:
			group.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	class Attachment {
		private ByteBuffer buffer;
		private AsynchronousSocketChannel client;

		public Attachment(ByteBuffer buffer, AsynchronousSocketChannel client) {
			super();
			this.buffer = buffer;
			this.client = client;
		}

		public ByteBuffer getBuffer() {
			return buffer;
		}

		public void setBuffer(ByteBuffer buffer) {
			this.buffer = buffer;
		}

		public AsynchronousSocketChannel getClient() {
			return client;
		}

		public void setClient(AsynchronousSocketChannel client) {
			this.client = client;
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		new Server().start("localhost", 12345);
	}

}
