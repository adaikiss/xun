/**
 * 
 */
package org.adaikiss.xun.unittest.powermock;

import java.util.Random;

/**
 * @author hlw
 * 
 */
public class ValueRepository {
	/**
	 * this method is mocked, never actually be call
	 * 
	 * @param index
	 * @return
	 */
	public static int get(int index) {
		return new Random().nextInt();
	}

	/**
	 * this method is mocked, never actually be call
	 * 
	 * @return
	 */
	@SuppressWarnings("unused")
	private int reset() {
		return new Random().nextInt();
	}

	/**
	 * this method is mocked, never actually be call
	 * 
	 */
	@SuppressWarnings("unused")
	private static void init() {

	}
}
