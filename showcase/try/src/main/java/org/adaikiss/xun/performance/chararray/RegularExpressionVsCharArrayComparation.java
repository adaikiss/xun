/**
 * 
 */
package org.adaikiss.xun.performance.chararray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author HuLingwei
 * 
 */
public class RegularExpressionVsCharArrayComparation {

	public static String readFile(String file) {
		InputStream is = RegularExpressionVsCharArrayComparation.class.getResourceAsStream(file);
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
	public static void main(String[] args) {
		String docPath = "/data/Illusion-c1.txt";
		String doc = readFile(docPath);
		String word = "me";
		int times = 5000;
		Long result1 = Stater.stat(new CharArrayExecutor(doc, word), times);
		System.out.println("result:" + result1);
		Long result2 = Stater.stat(new RegularExpressionExecutor(doc, word), times);
		System.out.println("result:" + result2);
	}

	static class CharArrayExecutor implements Executable<Long> {
		private String doc, word;

		public CharArrayExecutor(String doc, String word) {
			this.doc = doc;
			this.word = word;
		}

		private boolean isNotLetter(char c){
			return c < 'A' || (c > 'Z' && c < 'a') || c > 'z';
		}

		@Override
		public String getDescription() {
			return "CharArrayExecutor";
		}

		@Override
		public Long execute() {
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
	}

	static class RegularExpressionExecutor implements Executable<Long> {
		private String doc, word;

		public RegularExpressionExecutor(String doc, String word) {
			this.doc = doc;
			this.word = word;
		}

		@Override
		public String getDescription() {
			return "RegularExpressionExecutor";
		}

		public Long execute() {
			long count = 0;
			for (String s : doc.split("(\\s|\\p{Punct})+")) {
				if (word.equals(s)) {
					count++;
				}
			}
			return count;
		}
	}
}
