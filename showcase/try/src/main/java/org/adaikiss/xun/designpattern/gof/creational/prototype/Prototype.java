/**
 * 
 */
package org.adaikiss.xun.designpattern.gof.creational.prototype;

import org.adaikiss.xun.designpattern.gof.creational.abstractfactory.AbstractFactory;
import org.adaikiss.xun.designpattern.gof.structural.composite.Composite;
import org.adaikiss.xun.designpattern.gof.structural.decorator.Decorator;

/**
 * <b>Prototype</b>
 * 
 * <pre>
 * Definition
 *   Cloning an object by reducing the cost of creation.
 * Where to use & benefits
 *   When there are many subclasses that differ only in the kind of objects,
 *   A system needs independent of how its objects are created, composed, and represented.
 *   Dynamic binding or loading a method.
 *   Use one instance to finish job just by changing its state or parameters.
 *   Add and remove objects at runtime.
 *   Specify new objects by changing its structure.
 *   Configure an application with classes dynamically.
 *   Related patterns include
 *     {@link AbstractFactory}, which is often used together with prototype. An abstract factory may store some prototypes for cloning and returning objects.
 *     {@link Composite}, which is often used with prototypes to make a part-whole relationship.
 *     {@link Decorator}, which is used to add additional functionality to the prototype.
 * </pre>
 * 
 * @author hlw
 * 
 */
public class Prototype {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		HeavyGun ak47 = new HeavyGun("AK47");
		ak47.attack();
		HeavyGun m16 = ak47.clone();
		m16.setName("m16");
		m16.attack();
	}

}
