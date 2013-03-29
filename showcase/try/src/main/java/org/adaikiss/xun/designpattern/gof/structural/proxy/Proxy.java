/**
 * 
 */
package org.adaikiss.xun.designpattern.gof.structural.proxy;

import org.adaikiss.xun.designpattern.gof.structural.adapter.Adapter;
import org.adaikiss.xun.designpattern.gof.structural.decorator.Decorator;

/**
 * <b>Proxy</b>
 * 
 * <pre>
 * Definition
 *   Use a simple object to represent a complex one or provide a placeholder for another object to control access to it.
 * Where to use & benefits
 *   If creating an object is too expensive in time or memory.
 *   Postpone the creation until you need the actual object.
 *   Load a large image (time consuming).
 *   Load a remote object over network during peak periods.
 *   Access right is required to a complex system.
 *   Related patterns include
 *     {@link Adapter} pattern, which provides a different interface to the object it adapts, whereas a proxy provides the same interface as its subject, and
 *     {@link Decorator} pattern, which focuses on adding new functions to an object, whereas a proxy controls access to the object.
 * </pre>
 * 
 * @author hlw
 * 
 */
public class Proxy {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new ZooKeeper(new ZooImpl("Station Zoo")).getZoo().visit("Jackson");
	}
}
