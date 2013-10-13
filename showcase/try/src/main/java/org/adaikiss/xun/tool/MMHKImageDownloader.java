/**
 * 
 */
package org.adaikiss.xun.tool;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.imageio.ImageIO;

/**
 * @author HuLingwei
 *
 */
public class MMHKImageDownloader {
	static int BUFF_SIZE = 1024 * 10;
	static String PREFIX = "http://cdn.hommk.com/heroes/1.5.0.2-9-CN/";
	static final String BASE_DIR = "D:/heros/";
	ExecutorService pool = Executors.newFixedThreadPool(20);

	private void readFile(String path) throws Exception{
		File file = new File(path);
		FileReader r = null;
		char[] doc = null;
		try {
			r= new FileReader(file);
			char[] buff = new char[BUFF_SIZE];
			int readed = -1;
			while((readed = r.read(buff)) != -1){
				if(null == doc){
					doc = Arrays.copyOf(buff, buff.length);
					continue;
				}
				char[] doc1 = Arrays.copyOf(doc, doc.length + readed);
				doc = null;
				doc = doc1;
				doc1 = null;
				System.arraycopy(buff, 0, doc, doc.length - readed, readed);
			}
		} finally{
			if(r != null){
				try {
					r.close();
				} catch (Exception e) {
				}
			}
		}
		analyse(doc);
	}

	private void analyse(char[] doc){
//		System.out.println(new String(doc));
		int prefix = PREFIX.length();
		Map<String, String> images = new HashMap<String, String>();
		boolean start = false;
		StringBuilder sb = new StringBuilder();
		for(int i = 0;i < doc.length;i++){
			if(start){
//				if(doc[i] == '\''){
				if(doc[i] == ')'){
					start = false;
					if(sb.charAt(0) == '\'' || sb.charAt(0) == '"'){
						sb.deleteCharAt(0);
						sb.deleteCharAt(sb.length() - 1);
					}
					images.put(sb.substring(prefix), sb.toString());
					sb = null;
					sb = new StringBuilder();
					continue;
				}
				sb.append(doc[i]);
			}
//			if(doc[i] == '\'' && i > 5 && doc[i - 1] == '(' && doc[i - 2] == 'l' && doc[i - 3] == 'r'){
			if(doc[i] == '(' && i > 5 && doc[i - 1] == 'l' && doc[i - 2] == 'r' && doc[i - 3] == 'u'){
				start = true;
			}
		}
		CountDownLatch latch = new CountDownLatch(images.size());
		for(Map.Entry<String, String> entry : images.entrySet()){
			download(entry.getKey(), entry.getValue(), latch);
		}
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		pool.shutdown();
	}

	private void download(String name, String url, CountDownLatch latch){
		pool.submit(new DownloadTask(name, url, latch));
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		MMHKImageDownloader downloader = new MMHKImageDownloader();
		
//		downloader.readFile("D:/prod.css");
		downloader.readFile("D:/prod_CTU.css");
//		new Thread(downloader.new DownloadTask("img/css_sprite/worldMap_Arrows.gif", "http://cdn.hommk.com/heroes/1.5.0.2-9-CN/img/css_sprite/worldMap_Arrows.gif", new CountDownLatch(1))).start();
	}

	class DownloadTask implements Runnable{
		private String name;
		private String url;
		private CountDownLatch latch;
		public DownloadTask(String name, String url, CountDownLatch latch){
			this.name = name;
			this.url = url;
			this.latch = latch;
		}

		@Override
		public void run() {
			URLConnection conn = null;
			File file = new File(BASE_DIR + name);
//			System.out.println(file.getAbsolutePath());
			if(file.exists()){
				return;
			}
			try {
				conn = new URL(url).openConnection();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			InputStream is = null;
			FileOutputStream out = null;
			try {
				is = conn.getInputStream();
				file.getParentFile().mkdirs();
				file.createNewFile();
				BufferedImage  bufferedImage=ImageIO.read(is);
				ImageIO.write(bufferedImage, name.substring(name.length() - 3), file);
//				byte[] b = new byte[is.available()];
//				is.read(b);
//				out = new FileOutputStream(file);
//				out.write(b);
//				out.flush();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(name);
				System.out.println(url);
			}finally{
				latch.countDown();
				if(out != null){
					try {
						out.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

}
