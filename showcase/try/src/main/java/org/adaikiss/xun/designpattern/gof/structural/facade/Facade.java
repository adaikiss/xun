/**
 * 
 */
package org.adaikiss.xun.designpattern.gof.structural.facade;

import org.adaikiss.xun.designpattern.gof.behavioral.mediator.Mediator;
import org.adaikiss.xun.designpattern.gof.creational.abstractfactory.AbstractFactory;
import org.adaikiss.xun.designpattern.gof.creational.singleton.Singleton;

/**
 * <b>Facade</b>
 * 
 * <pre>
 * Definition
 *   Make a complex system simpler by providing a unified or general interface, which is a higher layer to these subsystems.
 * Where to use & benefits
 *   Want to reduce complexities of a system.
 *   Decouple subsystems , reduce its dependency, and improve portability.
 *   Make an entry point to your subsystems.
 *   Minimize the communication and dependency between subsystems.
 *   Security and performance consideration.
 *   Shield clients from subsystem components.
 *   Simplify generosity to specification.
 *   Related patterns include
 *     {@link AbstractFactory}, which is often used to create an interface for a subsystem in an independent way, and can be used as an alternative way to a facade.
 *     {@link Singleton}, which is often used with a facade.
 *     {@link Mediator}, which is similar to facade, but a facade doesn't define new functionality to the subsystem.
 * </pre>
 * 
 * @author hlw
 * 
 */
public class Facade {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Department.talk(Business.Consulting);
	}

}
