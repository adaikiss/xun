/**
 * 上午11:39:18
 */
package org.adaikiss.kay.trys.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

/**
 * hlw
 *
 */
public class ReadSourceTry {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException{
		URL url = new URL("http://www.163.com");
	    URLConnection conn = url.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
		PrintWriter out = new PrintWriter(System.out, true);
		String s;
		while((s = in.readLine())!=null){
			out.println(s);
		}
		in.close();
		out.close();
	}

}
