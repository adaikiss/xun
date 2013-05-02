/**
 * 
 */
package org.adaikiss.xun.unittest.powermock;


/**
 * @author hlw
 * 
 */
public class PowerMockShowcase {

	/**
	 * get value from repository and plus 10
	 * 
	 * @param index
	 * @return
	 */
	public int getValue(int index) {
		return ValueRepository.get(index) + 10;
	}
}
