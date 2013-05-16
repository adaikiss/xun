/**
 * 
 */
package org.adaikiss.xun.designpattern.gof.creational.singleton;

/**
 * @author hlw
 * 
 */
public class GunSynchronized {
	private static GunSynchronized instance;

	private GunSynchronized() {
		System.out.println("you get a gun!");
	}

	public synchronized static GunSynchronized get() {
		if (instance == null) {
			instance = new GunSynchronized();
		}
		return instance;
	}

	public void attack() {
		System.out.println("shoots you!");
	}
}
