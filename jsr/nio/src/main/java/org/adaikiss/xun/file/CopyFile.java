/**
 * 
 */
package org.adaikiss.xun.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;

/**
 * @author hlw
 * 
 */
public class CopyFile {

	private static int BUFFER_SIZE = 1024 * 8;
	public static void copyFile_io(File source, File dest) throws IOException {
		if (!dest.exists()) {
			dest.createNewFile();
		}
		InputStream in = null;
		OutputStream out = null;
		try {
			in = new FileInputStream(source);
			out = new FileOutputStream(dest);
			// Transfer bytes from in to out
			byte[] buf = new byte[BUFFER_SIZE];
			int len;
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
		} finally {
			if (in != null) {
				in.close();
			}
			if (out != null) {
				out.close();
			}
		}
	}

	public static void copyFile_nio(File sourceFile, File destFile)
			throws IOException {
		if (!destFile.exists()) {
			destFile.createNewFile();
		}

		FileChannel source = null;
		FileChannel destination = null;
		try {
			source = new FileInputStream(sourceFile).getChannel();
			destination = new FileOutputStream(destFile).getChannel();
			long count = 0;
			long size = source.size();
			while((count += destination.transferFrom(source, count, BUFFER_SIZE))<size);
			destination.transferFrom(source, 0, source.size());
		} finally {
			if (source != null) {
				source.close();
			}
			if (destination != null) {
				destination.close();
			}
		}
	}

	public static void compare(File source, File dest_io, File dest_nio) throws IOException{
		long begin = System.currentTimeMillis();
		copyFile_io(source, dest_io);
		System.out.print(System.currentTimeMillis() - begin);
		System.out.print(" ");
		begin = System.currentTimeMillis();
		copyFile_nio(source, dest_nio);
		System.out.println(System.currentTimeMillis() - begin);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		File source = new File("c:/nio.copy.sample.source.txt");
		File dest_io = new File("c:/nio.copy.sample.dest_io.txt");
		File dest_nio = new File("c:/nio.copy.sample.dest_nio.txt");
		for(int i = 0;i < 20; i++ ){
			dest_io.deleteOnExit();
			dest_nio.deleteOnExit();
			compare(source, dest_io, dest_nio);
		}
	}

}
