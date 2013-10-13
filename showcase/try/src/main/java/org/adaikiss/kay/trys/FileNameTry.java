package org.adaikiss.kay.trys;

import java.io.File;

public class FileNameTry {
	public static void main(String[] args) throws Exception{
		String folder = FileNameTry.class.getResource("").getFile();
		File file = new File(folder);
		System.out.println(file.getAbsolutePath());
		System.out.println(file.getParent());
	}
}
