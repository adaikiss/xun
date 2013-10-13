/**
 * 下午04:59:45
 */
package org.adaikiss.kay.trys.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

/**
 * hlw
 * 
 */
public class Server extends Thread {
	private ServerSocket ss;
	private List<ServerThread> clients = new ArrayList<ServerThread>();

	@Override
	public void run() {
		try {
			while (true) {
				Socket s = ss.accept();
				ServerThread st = new ServerThread(s);
				clients.add(st);
				st.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private class ServerThread extends Thread {
		private Socket s;

		public ServerThread(Socket s) {
			this.s = s;
		}

		@Override
		public void run() {
			try {
				BufferedReader in = new BufferedReader(new InputStreamReader(
						s.getInputStream()));
				String str;
				while ((str = in.readLine()) != null) {
					System.out.println("Server received:" + str);
				}
				in.close();
				s.close();
			} catch (SocketException e) {
				System.out.println("Client closed!");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public Server(int port) {
		try {
			this.ss = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String... args) {
		Server server = new Server(17668);
//		server.setDaemon(true);
		server.start();
	}
}
