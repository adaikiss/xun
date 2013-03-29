/**
 * 
 */
package org.adaikiss.xun.designpattern.gof.creational.factory;

/**
 * @author hlw
 * 
 */
public class WeaponFactory {
	enum WeaponType {
		Gun, Knife
	}

	public static Weapon create(WeaponType weaponType) {
		switch (weaponType) {
		case Gun:
			return new Gun();
		case Knife:
			return new Knife();
		default:
			return null;
		}
	}
}
