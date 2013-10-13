/**
 * @date 2011-5-5
 */
package org.adaikiss.kay.trys;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.adaikiss.kay.utils.DateUtils;

/**
 * @author hlw
 *
 */
public class DateTry {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		Date date = new Date();
		Date date2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2011-04-06 22:45:33");
		System.out.println(DateUtils.daysBetween(date, date2));
	}

}
