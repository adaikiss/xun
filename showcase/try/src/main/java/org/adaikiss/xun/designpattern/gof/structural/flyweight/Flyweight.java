/**
 * 
 */
package org.adaikiss.xun.designpattern.gof.structural.flyweight;

import org.adaikiss.xun.designpattern.gof.behavioral.state.State;
import org.adaikiss.xun.designpattern.gof.behavioral.strategy.Strategy;
import org.adaikiss.xun.designpattern.gof.creational.factory.Factory;
import org.adaikiss.xun.designpattern.gof.structural.composite.Composite;

/**
 * <b>Flyweight</b>
 * 
 * <pre>
 * Definition
 *   Make instances of classes on the fly to improve performance efficiently, like individual characters or icons on the screen.
 * Where to use & benefits
 *   Need to instantiate a large amount of small and fine-grained classes.
 *   Need icons to represent object.
 *   An object extrinsic state can be shared by classes.
 *   Reduce the number of objects created, decrease memory footprint and increase performance.
 *   Increase runtime cost associated with transferring, finding, or computing extrinsic data.
 *   Related patterns include
 *     {@link Composite}, which supports recursive structures, whereas an flyweight is often applied on it.
 *     {@link Factory} Method, which produces specific object upon requirement, whereas an flyweight uses it to reduce objects.
 *     {@link State}, which allows an object to alter its behavior when its internal state is changed, whereas a flyweight is best implemented on it.
 *     {@link Strategy}, which allows an algorithm vary independently to suit its needs, whereas a flyweight is based on such strategy.
 * </pre>
 * 
 * @author hlw
 * 
 */
public class Flyweight {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			java.util.Random rand = new java.util.Random();
			SnowFaller.fall(rand.nextInt(10));
		}
	}
}
