/**
 * 下午03:02:17
 */
package org.adaikiss.kay.trys.tij.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * hlw
 * 
 */
public class OSExecutorTry {

	public static void command(String cmd) throws IOException {
		boolean err = false;
		try {
			Process process = new ProcessBuilder(cmd.split(" ")).start();
			BufferedReader results = new BufferedReader(new InputStreamReader(
					process.getInputStream()));
			String s;
			while ((s = results.readLine()) != null) {
				System.out.println(s);
			}
			BufferedReader errors = new BufferedReader(new InputStreamReader(
					process.getErrorStream()));
			while ((s = errors.readLine()) != null) {
				System.out.println(s);
				err = true;
			}
		} catch (Exception e) {
			if (!cmd.startsWith("CMD /C"))
				command("CMD /C " + cmd);
			else
				throw new RuntimeException(e);
		}
		if (err) {
			System.out.println("Error Command:" + cmd);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		OSExecutorTry.command("javap E:/workspaces/kay2010/kay2010/trunk/kay/parent/web/src/test/java/org/adaikiss/kay/trys/tij/io/OSExecutorTry");
	}

}
