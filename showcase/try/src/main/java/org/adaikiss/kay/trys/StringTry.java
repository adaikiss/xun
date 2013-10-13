package org.adaikiss.kay.trys;

public class StringTry {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String c = "abc";
		System.out.println(c == c.intern());
		String a = "abc" + "def";
		System.out.println(a == a.intern());
		String b = "abcdef";
		System.out.println(a == b);
	}

}
