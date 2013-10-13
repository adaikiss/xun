package org.adaikiss.kay.trys;

public class BoolTry {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int a = 0;
		int b = 0;
		if ((a = 3) > 0 | (b = 3) > 0) {
			System.out.print(a);
			System.out.print(", ");
			System.out.print(b);
			System.out.println();
		}
		a = 0;
		b = 0;
		if ((a = 3) > 0 || (b = 3) > 0) {
			System.out.print(a);
			System.out.print(", ");
			System.out.print(b);
			System.out.println();
		}
	}

}
