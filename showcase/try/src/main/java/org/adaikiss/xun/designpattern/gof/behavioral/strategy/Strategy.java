/**
 * 
 */
package org.adaikiss.xun.designpattern.gof.behavioral.strategy;

import org.adaikiss.xun.designpattern.gof.behavioral.state.State;
import org.adaikiss.xun.designpattern.gof.structural.composite.Composite;
import org.adaikiss.xun.designpattern.gof.structural.decorator.Decorator;
import org.adaikiss.xun.designpattern.gof.structural.flyweight.Flyweight;

/**
 * <b>Strategy</b>
 * 
 * <pre>
 * Definition
 *   Group several algorithms in a single module to provide alternatives. Also known as policy.
 * Where to use & benefits
 *   Encapsulate various algorithms to do more or less the same thing.
 *   Need one of several algorithms dynamically.
 *   The algorithms are exchangeable and vary independently
 *   Configure a class with one of many related classes (behaviors).
 *   Avoid exposing complex and algorithm-specific structures.
 *   Data is transparent to the clients.
 *   Reduce multiple conditional statements.
 *   Provide an alternative to subclassing.
 *   Related patterns include
 *     {@link State}, which can activate several states, whereas a strategy can only activate one of the algorithms.
 *     {@link Flyweight}, which provides a shared object that can be used in multiple contexts simultaneously, whereas a strategy focuses on one context.
 *     {@link Decorator}, which changes the skin of an object, whereas a strategy changes the guts of an object.
 *     {@link Composite}, which is used to combine with a strategy to improve efficiency.
 * </pre>
 * 
 * @author hlw
 * 
 */
public class Strategy {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SortingClient client = new SortingClient();
		client.setStrategy(new BubbleSortingStrategy());
		client.sort(new int[]{5, 3, 4, 2, 11, 8, 1});
	}

}
