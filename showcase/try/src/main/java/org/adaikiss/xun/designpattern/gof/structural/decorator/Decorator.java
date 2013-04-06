/**
 * 
 */
package org.adaikiss.xun.designpattern.gof.structural.decorator;

/**
 * <b>Decorator</b>
 * 
 * <pre>
 * Definition
 *   Attach additional responsibilities or functions to an object dynamically or statically. Also known as Wrapper.
 * Where to use & benefits
 *   Provide an alternative to subclassing.
 *   Add new function to an object without affecting other objects.
 *   Make a responsibility easily added and removed dynamically.
 *   More flexibility than static inheritance.
 *   Transparent to the object.
 *   Related patterns include
 *     {@link Adapter} pattern, which provides a different interface to the object it adapts, whereas a decorator changes an object's responsibilities,
 *     {@link Proxy} pattern, which controls access to the object, whereas a decorator focuses on adding new functions to an object,
 *     {@link Composite} pattern, which aggregates an object, whereas a decorator adds additional responsibilities to an object, and
 *     {@link Strategy} pattern, which changes the guts of an object, whereas a decorator changes the skin of an object.
 *     {@link Facade} pattern, which provides a way of hiding a complex class, whereas a decorator adds function by wrapping a class.
 * </pre>
 * 
 * @author hlw
 * 
 */
public class Decorator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		IronSword ironSword = new IronSword();
		WolfMan wolfMan = new WolfMan();
		ironSword.struck(wolfMan);
		SilverPlatedSword silverPlatedSword = new SilverPlatedSword(ironSword);
		silverPlatedSword.struck(wolfMan);
	}

}
