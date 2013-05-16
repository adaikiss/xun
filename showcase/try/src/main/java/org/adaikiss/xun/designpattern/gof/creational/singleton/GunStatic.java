/**
 * 
 */
package org.adaikiss.xun.designpattern.gof.creational.singleton;

/**
 * @author hlw
 * 
 */
public class GunStatic {
	private static GunStatic instance = new GunStatic();

	private GunStatic() {
		System.out.println("you get a gun!");
	}

	public static GunStatic get() {
		return instance;
	}

	public void attack() {
		System.out.println("shoots you!");
	}
}
