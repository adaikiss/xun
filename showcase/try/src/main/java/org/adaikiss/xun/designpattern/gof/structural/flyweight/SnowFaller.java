/**
 * 
 */
package org.adaikiss.xun.designpattern.gof.structural.flyweight;

/**
 * @author hlw
 *
 */
public class SnowFaller {
	public static void fall(int number){
		System.out.print(SnowFactory.getSnow(number));
	}
}
