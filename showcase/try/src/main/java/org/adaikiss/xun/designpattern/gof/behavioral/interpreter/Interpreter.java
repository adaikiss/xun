/**
 * 
 */
package org.adaikiss.xun.designpattern.gof.behavioral.interpreter;

import org.adaikiss.xun.designpattern.gof.behavioral.iterator.Iterator;
import org.adaikiss.xun.designpattern.gof.behavioral.visitor.Visitor;
import org.adaikiss.xun.designpattern.gof.structural.composite.Composite;
import org.adaikiss.xun.designpattern.gof.structural.flyweight.Flyweight;

/**
 * <b>Interpreter</b>
 * 
 * <pre>
 * Definition
 *   Provides a definition of a macro language or syntax and parsing into objects in a program.
 * 
 *   Given a language, define a representation for its grammar along with an interpreter that
 *     uses the representation to interpret sentences in the language.
 * 
 * Where to use & benefits
 *   Need your own parser generator.
 *   Translate a specific expression.
 *   Handle a tree-related information.
 *   Related patterns include
 *     {@link Composite}, which is an instance in an interpreter.
 *     {@link Flyweight}, which shows how to share symbols with abstract context.
 *     {@link Iterator}, which is used to traverse the tree structure.
 *     {@link Visitor}, which is used to maintain behavior of each note in tree structure.
 * </pre>
 * 
 * @author hlw
 * 
 */
public class Interpreter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JournalAccountManager manager = new JournalAccountManager();
		manager.interpret("Dinner|25|Chinese fast food");
		manager.interpret("Fare|12|Company to home");
	}
}
