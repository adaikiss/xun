/**
 * 
 */
package org.adaikiss.xun.designpattern.gof.structural.flyweight;

/**
 * @author hlw
 * 
 */
public class Snow {
	private String sign;

	public Snow(String sign) {
		this.sign = sign;
	}

	@Override
	public String toString() {
		return sign;
	}
}
