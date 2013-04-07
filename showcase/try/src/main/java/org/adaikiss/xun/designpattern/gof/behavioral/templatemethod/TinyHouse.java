/**
 * 
 */
package org.adaikiss.xun.designpattern.gof.behavioral.templatemethod;

/**
 * @author hlw
 * 
 */
public class TinyHouse extends House {
	protected double width = 9.2;
	protected double length = 8.5;
	protected double height = 2.3;

	@Override
	public String toString() {
		return "size:" + width + "x" + length + "x" + height;
	}
}
