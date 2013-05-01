/**
 * 
 */
package org.adaikiss.xun.unittest.mockito;

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
}
