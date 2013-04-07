/**
 * 
 */
package org.adaikiss.xun.designpattern.gof.creational.builder;

import org.adaikiss.xun.designpattern.gof.creational.abstractfactory.AbstractFactory;
import org.adaikiss.xun.designpattern.gof.structural.composite.Composite;

/**
 * <b>Builder</b>
 * 
 * <pre>
 * Definition
 *   Construct a complex object from simple objects step by step.
 * Where to use & benefits
 *   Make a complex object by specifying only its type and content. The built object is shielded from the details of its construction.
 *   Want to decouple the process of building a complex object from the parts that make up the object.
 *   Isolate code for construction and representation.
 *   Give you finer control over the construction process.
 *   Related patterns include
 *     {@link AbstractFactory}, which focuses on the layer over the factory pattern (may be simple or complex), whereas a builder pattern focuses on building a complex object from other simple objects.
 *     {@link Composite}, which is often used to build a complex object.
 * </pre>
 * 
 * @author hlw
 * 
 */
public class Builder {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Car();
	}

}
