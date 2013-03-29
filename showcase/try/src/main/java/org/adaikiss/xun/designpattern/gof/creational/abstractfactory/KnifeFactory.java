/**
 * 
 */
package org.adaikiss.xun.designpattern.gof.creational.abstractfactory;

/**
 * @author hlw
 *
 */
public class KnifeFactory{
	public static Weapon create(Weapon.Location location){
		switch(location){
		case USA:
			return new Gun("American");
		case Russian:
			return new Knife("Russian");
		default :
			return null;
		}
	}
}
