/**
 * 
 */
package org.adaikiss.xun.designpattern.gof.creational.singleton;

/**
 * @author hlw
 * 
 */
public class Gun {
	private static Gun instance;

	private Gun() {
		System.out.println("you get a gun!");
	}

	public synchronized static Gun get() {
		if (instance == null) {
			instance = new Gun();
		}
		return instance;
	}

	public void attack() {
		System.out.println("shoots you!");
	}
}
