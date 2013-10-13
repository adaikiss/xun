/**
 * 上午10:16:27
 */
package org.adaikiss.kay.trys;

/**
 * hlw
 *
 */
public class SplitTry {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String a = "\\\\,";
		String b = "\\\\" + ",";
		String str = "aa,bb,cc\\, dd,ee";
		System.out.println(str);
		System.out.println(a);
		System.out.println(b);
		System.out.println(str.replaceAll(a, "/|/"));
		System.out.println(str.replaceAll(b, "/|/"));
		System.out.println(str.replaceAll("\\\\,", "/|/"));
		System.out.println(str.indexOf(a));
		System.out.println(str.indexOf("\\,"));
	}
}
