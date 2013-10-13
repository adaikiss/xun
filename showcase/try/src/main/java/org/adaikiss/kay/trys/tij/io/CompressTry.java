/**
 * 下午03:36:14
 */
package org.adaikiss.kay.trys.tij.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.Adler32;
import java.util.zip.CheckedInputStream;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * hlw
 *
 */
public class CompressTry {

	public void compress(File zipFile, String...fileNames) throws IOException {
		FileOutputStream f = new FileOutputStream(zipFile);
		CheckedOutputStream csum = new CheckedOutputStream(f, new Adler32());
		ZipOutputStream zos = new ZipOutputStream(csum);
		BufferedOutputStream out = new BufferedOutputStream(zos);
		zos.setComment("A test of Java Zipping");
		for(String fileName:fileNames){
			BufferedReader in = new BufferedReader(new FileReader(fileName));
			zos.putNextEntry(new ZipEntry(fileName));
			int c;
			while((c = in.read())!=-1){
				out.write(c);
			}
			in.close();
			out.flush();
		}
		out.close();
	}

	public void unCompress(File zipFile) throws IOException{
		FileInputStream f = new FileInputStream(zipFile);
		CheckedInputStream csum = new CheckedInputStream(f, new Adler32());
		ZipInputStream zis = new ZipInputStream(csum);
		BufferedInputStream in = new BufferedInputStream(zis);
		ZipEntry ze;
		while((ze = zis.getNextEntry())!=null){
			System.out.println("Reading file " + ze);
			int x;
			while((x = in.read())!=-1){
				System.out.println(x);
			}
		}
		in.close();
		ZipFile zip = new ZipFile(zipFile);
		Enumeration<? extends ZipEntry> e = zip.entries();
		while(e.hasMoreElements()){
			ZipEntry ze2 = (ZipEntry)e.nextElement();
			System.out.println(ze2);
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args){
	}

}
