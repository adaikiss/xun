/**
 * 2011-3-29
 */
package org.adaikiss.kay.trys;

/**
 * hlw
 *
 */
public class StringAppendTry {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
		sb.append("aaa").append("26").append("bbb").append(26);
		System.out.println(sb);
		String s = "aaaxbbby";
		System.out.println(s.replaceAll("x", "26").replaceAll("y", String.valueOf(26)));
	}

}
