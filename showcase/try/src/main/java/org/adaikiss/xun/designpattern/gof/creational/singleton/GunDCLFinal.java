/**
 * 
 */
package org.adaikiss.xun.designpattern.gof.creational.singleton;

/**
 * java5 or newer. </br> In Java 5, a change was made to the definition of final
 * fields. Where the values of these fields are set in the constructor, the JVM
 * ensures that these values are committed to main memory before the object
 * reference itself. In other words, another thread that can "see" the object
 * cannot ever see uninitialised values of its final fields.
 * 
 * @author hlw
 * 
 */
public class GunDCLFinal {
	private static GunDCLFinal instance;

	private GunDCLFinal() {
		System.out.println("you get a gun!");
	}

	public synchronized static GunDCLFinal get() {
		if (instance == null) {
			synchronized (GunDCLFinal.class) {
				if (instance == null) {
					instance = new GunDCLFinal();
				}
			}
		}
		return instance;
	}

	public void attack() {
		System.out.println("shoots you!");
	}
}
