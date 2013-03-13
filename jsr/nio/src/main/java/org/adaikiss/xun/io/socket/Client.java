/**
 * 
 */
package org.adaikiss.xun.io.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author hlw
 *
 */
public class Client {

	private void start(String host, int port) throws IOException{
		Socket client = new Socket();
		client.connect(new InetSocketAddress(host, port));
		Scanner scanner = new Scanner(System.in);
		String msg;
		PrintWriter out = null;
		BufferedReader in = null;
		try {
			out = new PrintWriter(client.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			while(!"quit".equals(msg = scanner.nextLine())){
				out.println(msg);
				System.out.println("Client:received [" + in.readLine() + "]");
			}
		} catch (Exception e) {
			if(null != in){
				try {
					in.close();
				} catch (Exception e1) {
				}
			}
			if(null != out){
				out.close();
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		new Client().start("localhost", 12345);
	}

}
