/**
 * 
 */
package org.adaikiss.xun.designpattern.gof.behavioral.observer;

import org.adaikiss.xun.designpattern.gof.behavioral.mediator.Mediator;
import org.adaikiss.xun.designpattern.gof.creational.singleton.Singleton;

/**
 * <b>Observer</b>
 * 
 * <pre>
 * Definition
 *   One object changes state, all of its dependents are updated automatically.
 * Where to use & benefits
 *   One change affects one or many objects.
 *   Many object behavior depends on one object state.
 *   Need broadcast communication.
 *   AKA “Publish-Subscribe”.
 *   Maintain consistency between objects
 *   keep classes from becoming too tightly coupled, which would hamper reusability.
 *   Related patterns include
 *     {@link Singleton}, which is used to make observable object unique and accessible globally.
 *     {@link Mediator}, which is used to encapsulate updated objects.
 * </pre>
 * 
 * @author hlw
 * 
 */
public class Observer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Subscriber Jack = new Subscriber("Jack");
		Subscriber Jan = new Subscriber("Jan");
		Subscriber Jackson = new Subscriber("Jackson");
		Magazine magazine = new Magazine();
		magazine.subscribe(Jack, Jan, Jackson);
		magazine.setContent("Alize's Concert On Friday!");
		magazine.unsubscribe(Jan);
		magazine.setContent("Lady Ga Ga' Concert On Sunday!");
	}

}
