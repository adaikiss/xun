package org.adaikiss.kay.trys.reflect;

public class ArrayTry {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String a[] = new String[]{"aa", "bb", "cc"};
		Object o = java.lang.reflect.Array.get(a, 1);
		System.out.println(o);
	}

}
