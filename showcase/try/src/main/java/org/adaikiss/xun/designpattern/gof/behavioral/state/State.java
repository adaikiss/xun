/**
 * 
 */
package org.adaikiss.xun.designpattern.gof.behavioral.state;

import org.adaikiss.xun.designpattern.gof.creational.singleton.Singleton;
import org.adaikiss.xun.designpattern.gof.structural.flyweight.Flyweight;

/**
 * <b>State</b>
 * 
 * <pre>
 * Definition
 *   An object's behavior change is represented by its member classes, which share the same super class.
 * Where to use & benefits
 *   Need to control many states without using if-else or switch statements.
 *   Use a class to represent a state, not a constant or something else.
 *   Every state has to act in a similar manner.
 *   Every state must be a subclass of the same super class.
 *   Simplify and clarify the program.
 *   Related patterns include
 *     {@link Flyweight}, which explains when and how a state object can be shared.
 *     {@link Singleton} which is often used with state pattern to ensure some state change is shared with class itself, not instances of the class.
 * </pre>
 * 
 * @author hlw
 * 
 */
public class State {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TrafficLightController controller = new TrafficLightController();
		for(int i = 0;i < 10; i++){
			controller.shift();
			controller.show();
		}
	}

}
