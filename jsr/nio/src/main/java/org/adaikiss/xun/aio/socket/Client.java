/**
 * 
 */
package org.adaikiss.xun.aio.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.Scanner;

/**
 * @author hlw
 * 
 */
public class Client {

	private AsynchronousSocketChannel client;

	private void start(String host, int port) throws IOException {
		client = AsynchronousSocketChannel.open();
		client.connect(new InetSocketAddress(host, port), client,
				new CompletionHandler<Void, AsynchronousSocketChannel>() {

					@Override
					public void completed(Void result,
							AsynchronousSocketChannel attachment) {
						System.out.println("Client:connect succeeded!");
					}

					@Override
					public void failed(Throwable exc,
							AsynchronousSocketChannel attachment) {
						System.out.println("Client:connect failed!");
					}

				});
		readAndWrite();
	}

	private void readAndWrite() {
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		final Scanner scanner = new Scanner(System.in);
		Attachment attachment = new Attachment(buffer, client);
		String msg;
		client.read(buffer, attachment,
				new CompletionHandler<Integer, Attachment>() {

					@Override
					public void completed(Integer result, Attachment attachment) {
						if (result > 0) {
							System.out.println("Client:readed message [" + new String(attachment.getBuffer().array(), 0, result) + "]");
							attachment.getBuffer().clear();
							attachment.getClient().read(attachment.getBuffer(), attachment, this);
						}
					}

					@Override
					public void failed(Throwable exc, Attachment attachment) {
						System.out.println("Client:error reading messages!");
					}

				});
		while(!"quit".equals(msg = scanner.nextLine())){
			client.write(ByteBuffer.wrap(msg.getBytes()), client,
					new CompletionHandler<Integer, AsynchronousSocketChannel>() {

				@Override
				public void completed(Integer result,
						AsynchronousSocketChannel attachment) {
					System.out.println("Client:successfully writing messages!");
				}

				@Override
				public void failed(Throwable exc,
						AsynchronousSocketChannel attachment) {
					System.out.println("Client:error writing messages!");
				}
				
			});
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
		new Client().start("localhost", 12345);
	}

}
