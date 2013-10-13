package org.adaikiss.kay.trys;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class OutPutWriterTry {
	public static void main(String...args) throws Exception{
		File file = new File("e:/test.csv");
		OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
		out.write("abc", 0, 3);
		out.flush();
		out.close();
	}
}
