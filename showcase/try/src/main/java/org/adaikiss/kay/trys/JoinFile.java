/**
 * 2011-3-17
 */
package org.adaikiss.kay.trys;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * hlw
 * 
 */
public class JoinFile {
	public static List<String> read(String path) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader(path));
		List<String> list = new ArrayList<String>();
		String line;
		while ((line = in.readLine()) != null) {
			list.add(line);
		}
		in.close();
		return list;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		String keyPath = "e:/key.txt";
		String valuePath = "e:/value.txt";
		String resultPath = "e:/result.txt";
		List<String> key = read(keyPath);
		List<String> value = read(valuePath);
		BufferedWriter out = new BufferedWriter(new FileWriter(resultPath));
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < key.size(); i++) {
			sb.append(key.get(i)).append(" ").append(value.get(i)).append("\n");
		}
		out.append(sb.toString());
		out.flush();
		out.close();
	}

}
