/**
 * 
 */
package org.adaikiss.xun.designpattern.gof.creational.singleton;

/**
 * only java5 or newer
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
			synchronized(GunDCLVolatile.class){
				if(instance == null){
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
