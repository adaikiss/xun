/**
 * 
 */
package org.adaikiss.xun.designpattern.gof.creational.singleton;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.adaikiss.xun.designpattern.gof.creational.abstractfactory.AbstractFactory;
import org.adaikiss.xun.designpattern.gof.creational.builder.Builder;
import org.adaikiss.xun.designpattern.gof.creational.prototype.Prototype;

/**
 * <b>Singleton</b>
 * 
 * <pre>
 * Definition
 *   One instance of a class or one value accessible globally in an application.
 * Where to use & benefits
 *   Ensure unique instance by defining class final to prevent cloning.
 *   May be extensible by the subclass by defining subclass final.
 *   Make a method or a variable public or/and static.
 *   Access to the instance by the way you provided.
 *   Well control the instantiation of a class.
 *   Define one value shared by all instances by making it static.
 *   Related patterns include
 *     {@link AbstractFactory}, which is often used to return unique objects.
 *     {@link Builder}, which is used to construct a complex object, whereas a singleton is used to create a globally accessible object.
 *     {@link Prototype}, which is used to copy an object, or create an object from its prototype, whereas a singleton is used to ensure that only one prototype is guaranteed.
 * </pre>
 * 
 * @author hlw
 * 
 */
public class Singleton {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		ExecutorService executor = Executors.newFixedThreadPool(10);
		for (int i = 0; i < 50; i++) {
			executor.submit(new AttackThread());
		}
		executor.shutdown();
	}

	static class AttackThread implements Runnable {

		@Override
		public void run() {
			Gun.get().attack();
		}
	}
}
