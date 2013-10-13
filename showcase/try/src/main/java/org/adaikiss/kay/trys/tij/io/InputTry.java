/**
 * 下午02:09:21
 */
package org.adaikiss.kay.trys.tij.io;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;

/**
 * hlw
 * 
 */
public class InputTry {

	// Buffered input file
	public void read1(String fileName) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(fileName));
		String s;
		StringBuilder sb = new StringBuilder();
		while ((s = in.readLine()) != null) {
			sb.append(s + "\n");
		}
		in.close();
		System.out.println(sb.toString());
	}

	// Input from memory
	public void read2(String str) throws IOException {
		StringReader in = new StringReader(str);
		int c;
		while ((c = in.read()) != -1) {
			System.out.println((char) c);
		}
	}

	// Formatted memory input
	public void read2() throws IOException {
		DataInputStream in = new DataInputStream(new ByteArrayInputStream(
				"FormattedMemoryInput.java".getBytes()));
		while (in.available() != 0) {
			System.out.println((char) in.readByte());
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String s;
		while((s = in.readLine())!=null){
			System.out.println(s);
		}
	}

}
