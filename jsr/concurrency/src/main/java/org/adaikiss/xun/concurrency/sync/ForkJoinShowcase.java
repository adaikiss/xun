/**
 * 
 */
package org.adaikiss.xun.concurrency.sync;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @author HuLingwei
 *
 */
public class ForkJoinShowcase {

	private static boolean isNotLetter(char c){
		return c < 'A' || (c > 'Z' && c < 'a') || c > 'z';
	}

	static long count1(String doc, String word) {
		char[] w = word.toCharArray();
		char[] chars = doc.toCharArray();
		if (w.length > chars.length) {
			return 0l;
		}
		long count = 0;
		boolean suitPrefix = true;
		for (int i = 0; i < chars.length; i++) {
			if (suitPrefix && (i + w.length <= chars.length)) {
				for (int j = 0; j < w.length; j++) {
					if (chars[i + j] != w[j]) {
						break;
					}
					//word matches, the next char is not word or no more char.
					if (j == w.length - 1 && (i + w.length == chars.length || isNotLetter(chars[i + w.length]))) {
						i = i + w.length;
						count++;
					}
				}
			}
			suitPrefix = isNotLetter(chars[i]);
		}
		return count;
    }

	static long count2(String doc, String word){
		long count = 0;
		 for(String s : doc.split("(\\s|\\p{Punct})+")){
			if(word.equals(s)){
				count++;
			}
		 }
		 return count;
	}

	public static String readFile(String file) {
		InputStream is = ForkJoinShowcase.class.getResourceAsStream(file);
		StringBuilder sb = new StringBuilder();
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(is));
			char[] buf = new char[1024];
			int read;
			while ((read = in.read(buf)) != -1) {
				sb.append(buf, 0, read);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return sb.toString();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		String docPath = "/data/Illusion.txt";
		String doc = readFile(docPath);
		char[] c = doc.toCharArray();
		String word = "me";
		WordCountTask task1 = new WordCountTask(new String(c, 0, c.length/3), word);
		WordCountTask task2 = new WordCountTask(new String(c, c.length/3, c.length/3 * 2), word);
		WordCountTask task3 = new WordCountTask(new String(c, c.length/3 * 2, c.length - c.length/3 * 2), word);
		ForkJoinPool forkJoinPool = new ForkJoinPool();
		forkJoinPool.invoke(task1);
		forkJoinPool.invoke(task2);
		forkJoinPool.invoke(task3);
		forkJoinPool.shutdown();
		long result = 0;
		result += task1.join();
		result += task2.join();
		result += task3.join();
		System.out.println(result);
	}

	@SuppressWarnings("serial")
	static class WordCountTask extends RecursiveTask<Long>{
		private String doc, word;
		public WordCountTask(String doc, String word){
			this.doc = doc;
			this.word = word;
		}

		@Override
		protected Long compute() {
			return count1(doc, word);
		}
		
	}
}
