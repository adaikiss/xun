/**
 * 
 */
package org.adaikiss.xun.designpattern.gof.behavioral.templatemethod;

/**
 * @author hlw
 * 
 */
public abstract class House {
	protected double width;
	protected double length;
	protected double height;

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}
}
