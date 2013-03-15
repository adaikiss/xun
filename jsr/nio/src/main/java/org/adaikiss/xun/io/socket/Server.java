/**
 * 
 */
package org.adaikiss.xun.io.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author hlw
 * 
 */
public class Server {

	private void start(int port) throws IOException {
		ServerSocket server = new ServerSocket(port, 100);
		server.setReuseAddress(true);
		Executor executor = Executors.newCachedThreadPool();
		Socket socket;
		while ((socket = server.accept()) != null) {
			System.out.println("Server:a client connected!");
			executor.execute(new ServerHandler(socket));
		}
	}

	class ServerHandler implements Runnable {

		private Socket client;

		private boolean running = true;

		public ServerHandler(Socket client) {
			this.client = client;
		}

		@Override
		public void run() {
			BufferedReader in = null;
			PrintWriter out = null;
			try {
				in = new BufferedReader(new InputStreamReader(
						client.getInputStream()));
				out = new PrintWriter(client.getOutputStream(), true);
				while (running) {
					String msg = in.readLine();
					System.out.println("Server:received [" + msg + "]");
					out.println("server sent " + msg);
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally{
				if(in != null){
					try {
						in.close();
					} catch (IOException e) {
					}
				}
				if(out != null){
					out.close();
				}
			}
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		new Server().start(12345);
	}

}
