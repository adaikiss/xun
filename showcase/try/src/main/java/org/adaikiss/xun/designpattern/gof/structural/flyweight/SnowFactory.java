/**
 * 
 */
package org.adaikiss.xun.designpattern.gof.structural.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hlw
 * 
 */
public class SnowFactory {
	private static Map<Integer, Snow> numberedSigns = new HashMap<Integer, Snow>();

	public static Snow getSnow(int number) {
		Snow sign = numberedSigns.get(number);
		if ( sign == null) {
			sign = new Snow(getSnowString(number));
			numberedSigns.put(number, sign);
		}
		return sign;
	}

	private static String getSnowString(int number) {
		switch (number) {
		case 1:
			return "❆";
		case 2:
			return "❅";
		case 3:
			return "❄";
		case 4:
			return "✻";
		case 5:
			return "❈";
		case 6:
			return "✲";
		case 7:
			return "❇";
		case 8:
			return "✳";
		case 9:
			return "✽";
		default:
			return "✵";
		}
	}
}
