/**
 * 
 */
package org.adaikiss.xun.designpattern.gof.creational.abstractfactory;

import org.adaikiss.xun.designpattern.gof.creational.abstractfactory.WeaponFactory.WeaponType;
import org.adaikiss.xun.designpattern.gof.creational.factory.Factory;
import org.adaikiss.xun.designpattern.gof.creational.prototype.Prototype;
import org.adaikiss.xun.designpattern.gof.creational.singleton.Singleton;
import org.adaikiss.xun.designpattern.gof.structural.facade.Facade;

/**
 * <b>Abstract Factory</b>
 * 
 * <pre>
 * Definition
 *   Provides one level of interface higher than the {@link Factory} pattern. 
 *   It is used to return one of several factories.
 * 
 * Where to use & benefits
 *   Creates families of related or dependent objects like Kit.
 *   Provides a class library of products, exposing interface not implementation.
 *   Needs to isolate concrete classes from their super classes.
 *   A system needs independent of how its products are created, composed, and represented.
 *   Try to enforce a constraint.
 *   An alternative to {@link Facade} to hide platform-specific classes
 *   Easily extensible to a system or a family
 *   Related patterns include
 *     {@link Factory} method, which is often implemented with an abstract factory.
 *     {@link Singleton}, which is often implemented with an abstract factory.
 *     {@link Prototype}, which is often implemented with an abstract factory.
 *     {@link Facade}, which is often used with an abstract factory by providing an interface for creating implementing class.
 * </pre>
 * 
 * @author hlw
 * 
 */
public class AbstractFactory {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		WeaponFactory.create(WeaponType.Gun).attack();
		WeaponFactory.location = Weapon.Location.Russian;
		WeaponFactory.create(WeaponType.Gun).attack();
	}

}
