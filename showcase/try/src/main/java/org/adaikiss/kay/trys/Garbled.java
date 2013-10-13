package org.adaikiss.kay.trys;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;

public class Garbled {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("D:\\CharterTransform.java"), "ISO-8859-1"));
		String line = null;
		int l = 0;
		while((line = in.readLine()) != null){
			l++;
			if(l != 22){
				continue;
			}
			System.out.println(line);
			System.out.println(new String(line.getBytes("GBK"), "UTF-8"));
			System.out.println(new String(line.getBytes("GB2312"), "UTF-8"));
			System.out.println(new String(line.getBytes("UTF-8"), "GB2312"));
			System.out.println(new String(line.getBytes("UTF-8"), "ISO-8859-1"));
			System.out.println(new String(new String(line.getBytes("UTF-8"), "ISO-8859-1").getBytes("UTF-8"), "ISO-8859-1"));
		}
	}

}
