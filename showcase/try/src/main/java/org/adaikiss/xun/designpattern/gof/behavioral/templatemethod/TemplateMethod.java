/**
 * 
 */
package org.adaikiss.xun.designpattern.gof.behavioral.templatemethod;

import org.adaikiss.xun.designpattern.gof.behavioral.strategy.Strategy;
import org.adaikiss.xun.designpattern.gof.creational.factory.Factory;

/**
 * <b>Template Method</b>
 * 
 * <pre>
 * Definition
 *   Provide an abstract definition for a method or a class and redefine its behavior later or on the fly without changing its structure.
 * Where to use & benefits
 *   To make many similar operations template.
 *   From many specialized operations to a generalized operation.
 *   Refactor common behavior to simplify code.
 *   Algorithm related improvement.
 *   Need good coding skill to conquer it.
 *   May be hard to read for novice.
 *   Easy to produce ambiguity if not written well.
 *   Related patterns include
 *     {@link Factory} Method, which is combined with template method.
 *     {@link Strategy}, which is used to delegate or coordinate the template method.
 * </pre>
 * 
 * @author hlw
 * 
 */
public class TemplateMethod {

	public static void main(String[] args) {
		House tiny = new TinyHouse();
		System.out.println(tiny);
	}
}
