package org.adaikiss.kay.trys.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	private Socket s;
	public void start(){
		try {
			s = new Socket("127.0.0.1", 17668);
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			String str;
			while((str = in.readLine())!=null){
				if("exit".equals(str)){
					break;
				}
				out.write(str);
				out.newLine();
				out.flush();
			}
			out.close();
			in.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]){
		new Client().start();
	}
}
