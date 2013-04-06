/**
 * 
 */
package org.adaikiss.xun.designpattern.gof.structural.composite;

/**
 * <b>Composite</b>
 * 
 * <pre>
 * Definition
 *   Build a complex object out of elemental objects and itself like a tree structure.
 * Where to use & benefits
 *   Want to represent a part-whole relationship like tree folder system
 *   Group components to form larger components, which in turn can be grouped to form still larger components.
 *   Related patterns include
 *     {@link Decorator}, which is often used with composite pattern and with the same parent class.
 *     {@link Flyweight}, which is used with composite pattern to share components.
 *     {@link Iterator}, which is used to traverse the composites.
 *     {@link Visitor}, which localizes operations across composite and leaf classes.
 * </pre>
 * 
 * @author hlw
 * 
 */
public class Composite {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(new Company("XUN LTD.", new CustomerServiceDepartment(),
				new TechnologySupportDepartment(), new MarketDepartment()));
	}

}
