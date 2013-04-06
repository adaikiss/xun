/**
 * 
 */
package org.adaikiss.xun.designpattern.gof.creational.abstractfactory;

/**
 * @author hlw
 *
 */
public class GunFactory {
	public static Weapon create(Weapon.Location location){
		switch(location){
		case USA:
			return new Gun("m16");
		case Russian:
			return new Gun("AK47");
		default :
			return null;
		}
	}
}
