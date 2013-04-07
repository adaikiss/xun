/**
 * 
 */
package org.adaikiss.xun.designpattern.gof.behavioral.iterator;

import org.adaikiss.xun.designpattern.gof.behavioral.memento.Memento;
import org.adaikiss.xun.designpattern.gof.creational.factory.Factory;
import org.adaikiss.xun.designpattern.gof.structural.composite.Composite;

/**
 * <b>Iterator</b>
 * 
 * <pre>
 * Definition
 *   Provide a way to move through a list of collection or aggregated objects without knowing its internal representations.
 * Where to use & benefits
 *   Use a standard interface to represent data objects.
 *   Use s standard iterator built in each standard collection, like List, Sort, or Map.
 *   Need to distinguish variations in the traversal of an aggregate.
 *   Similar to Enumeration class, but more effective.
 *   Need to filter out some info from an aggregated collection.
 *   Related patterns include
 *     {@link Composite}, which supports recursive structures, whereas an iterator is often applied on it.
 *     {@link Factory} Method, which provides appropriate instances needed by an iterator subclasses.
 *     {@link Memento}, which is often used with an Iterator. The iterator stores the memento internally.
 * </pre>
 * 
 * @author hlw
 * 
 */
public class Iterator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Class clazz = new Class("Class 3");
		clazz.addStudent(new Student(1, "Jack"), new Student(2, "Jan"),
				new Student(3, "Catalina"), new Student(4, "Lucy"));
		java.util.Iterator<Student> iterator = clazz.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}

}
