package org.adaikiss.kay.trys;

public class ReplaceTry {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String a = "1.aa2.bb3.cc";
		System.out.println(a.replaceAll("(\\d)", "\n$1"));
		System.out.println("\\adfdsf");
		System.out.println("\\adfdsf".replace("\\", "\\\\"));
	}

}
