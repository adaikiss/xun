/**
 * 
 */
package org.adaikiss.xun.designpattern.gof.behavioral.state;

/**
 * @author hlw
 * 
 */
public class TrafficLight {
	enum Color {
		Green, Red, Yellow
	}

	public void yellow() {
		this.color = Color.Yellow;
	}

	public void green() {
		this.color = Color.Green;
	}

	public void red() {
		this.color = Color.Red;
	}

	private Color color;

	public TrafficLight(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

	@Override
	public String toString() {
		return this.color.name();
	}
}
