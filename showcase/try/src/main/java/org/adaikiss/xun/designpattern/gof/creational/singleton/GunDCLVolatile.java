/**
 * 
 */
package org.adaikiss.xun.designpattern.gof.creational.singleton;

/**
 * only java5 or newer. </br> Note that this is OK as of Java 5 because the
 * definition of volatile was specifically changed to make it OK. Accessing a
 * volatile variable has the semantics of synchronization as of Java 5. In other
 * words Java 5 ensures that the unsycnrhonized volatile read must happen after
 * the write has taken place, and the reading thread will see the correct values
 * of all fields on MyFactory.
 * 
 * @author hlw
 * 
 */
public class GunDCLVolatile {
	private static volatile GunDCLVolatile instance;

	private GunDCLVolatile() {
		System.out.println("you get a gun!");
	}

	public static GunDCLVolatile get() {
		if (instance == null) {
			synchronized (GunDCLVolatile.class) {
				if (instance == null) {
					instance = new GunDCLVolatile();
				}
			}
		}
		return instance;
	}

	public void attack() {
		System.out.println("shoots you!");
	}
}
