/**
 * 下午02:24:42
 */
package org.adaikiss.kay.trys.tij.io;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * hlw
 *
 */
public class OutputTry {

	//Basic file output
	public void write(File file) throws IOException{
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file)));
		out.println("aaa");
		out.flush();
		out.close();
	}
	//Text file output shortcut
	public void write2(File file) throws IOException{
		PrintWriter out = new PrintWriter(file);
		out.println("bbb");
		out.flush();
		out.close();
	}
	//Storing and recovering data
	public void write3(File file) throws IOException{
		DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
		out.writeUTF("嗯");
		out.writeDouble(3.14159265358979464332387950288);
		out.flush();
		out.close();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PrintWriter out = new PrintWriter(System.out, true);
		out.println("aaaaa");
		out.close();
	}

}
