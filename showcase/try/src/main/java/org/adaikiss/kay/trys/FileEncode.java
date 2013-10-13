/**
 * 
 */
package org.adaikiss.kay.trys;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * @author hlw
 *
 */
public class FileEncode {

	public static void GBK2UTF8(File file) {
		String path = file.getPath();
		FileInputStream in = null;
		OutputStreamWriter out = null;
		StringBuilder sb = new StringBuilder();
		byte[] buffers = new byte[1024];
		int length;
		try {
			in = new FileInputStream(file);
			while((length = in.read(buffers)) != -1){
				sb.append(new String(buffers, 0, length, "GBK"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if(in != null){				
				try {
					in.close();
				} catch (IOException e) {
				}
			}
		}
		if(sb.length() == 0){
			return;
		}
		String content = sb.toString();
		System.out.println(content);
		if(path.endsWith("jsp")){
			content = treatJsp(content);
		} else if(path.endsWith("html")){
			content = treatHtml(content);
		}
		try {
			out = new OutputStreamWriter(new FileOutputStream(file, false), "UTF-8");
			out.write(content);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if(out != null){
				try {
					out.close();
				} catch (IOException e) {
				}
			}
		}
	}

	public static String treatHtml(String content){
		if(content.contains("charset=gb2312")){
			content = content.replace("charset=gb2312", "charset=UTF-8");
		}
		if(content.contains("charset=GBK")) {
			content = content.replace("charset=GBK", "charset=UTF-8");
		}
		return content;
	}
	public static String treatJsp(String content){
		if(content.contains("charset=gb2312")) {
			content = content.replace("charset=gb2312", "charset=UTF-8");
		}
		if(content.contains("charset=GBK")) {
			content = content.replace("charset=GBK", "charset=UTF-8");
		}
		if(content.contains("pageEncoding=\"GBK\"")) {
			content = content.replace("pageEncoding=\"GBK\"", "pageEncoding=\"UTF-8\"");
		}
		if(content.contains("pageEncoding=\"gb2312\"")) {
			content = content.replace("pageEncoding=\"gb2312\"", "pageEncoding=\"UTF-8\"");
		}
		if(!content.contains("charset=UTF-8")){
			content = "<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\" pageEncoding=\"UTF-8\"%>\n" + content;
		}
		return content;
	}
	public static boolean filterFilenameSuffix(String filename, String[] suffixs){
		for(String suffix : suffixs){
			if(filename.endsWith(suffix)){
				return true;
			}
		}
		return false;
	}
	public static void readFiles(File root, String[] suffixs, boolean readSub){
		if(null == root || !root.exists()){
			return;
		}
		if(root.isFile() && filterFilenameSuffix(root.getPath(), suffixs)){
			GBK2UTF8(root);
			return;
		}
		if(readSub && root.isDirectory()){
			for(File file : root.listFiles()){
				readFiles(file, suffixs, readSub);
			}
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		readFiles(new File("E:\\svn\\zj_bhps"), new String[]{"jsp", "html", "properties", "txt", "js"}, true);
		readFiles(new File("E:\\svn\\zj_bhps\\src\\main\\webapp"), new String[]{"jsp", "html", "js", "txt"}, true);
//		GBK2UTF8(new File("E:\\sbp003init.jsp"));
	}

}
