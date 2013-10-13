/**
 * 
 */
package org.adaikiss.kay.trys;

import java.util.Calendar;

/**
 * @author hlw
 *
 */
public class CalendarTry {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Calendar c = Calendar.getInstance();
		c.set(2011, 0, 1, 0, 0, 0);
		c.set(Calendar.MILLISECOND, 0);
		System.out.println(c.getTime());
	}

}
