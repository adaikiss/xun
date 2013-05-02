/**
 * 
 */
package org.adaikiss.xun.unittest;

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
	public int get(int index) {
		return new Random().nextInt();
	}

	/**
	 * show void mock, never actually be call
	 */
	public void reset() {

	}
}
