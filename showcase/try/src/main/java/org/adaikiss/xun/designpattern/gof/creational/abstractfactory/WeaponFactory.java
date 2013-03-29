/**
 * 
 */
package org.adaikiss.xun.designpattern.gof.creational.abstractfactory;

/**
 * @author hlw
 * 
 */
public class WeaponFactory {
	enum WeaponType {
		Gun, Knife
	}

	static Weapon.Location location = Weapon.Location.USA;

	public static Weapon create(WeaponType type) {
		switch (type) {
		case Gun:
			return GunFactory.create(location);
		case Knife:
			return KnifeFactory.create(location);
		default:
			return null;
		}
	}
}
