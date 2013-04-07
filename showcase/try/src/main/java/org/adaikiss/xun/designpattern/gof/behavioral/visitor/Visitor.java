/**
 * 
 */
package org.adaikiss.xun.designpattern.gof.behavioral.visitor;

import org.adaikiss.xun.designpattern.gof.behavioral.interpreter.Interpreter;
import org.adaikiss.xun.designpattern.gof.structural.composite.Composite;

/**
 * <b>Visitor</b>
 * 
 * <pre>
 * Definition
 *   Define a new operation to deal with the classes of the elements without changing their structures.
 * Where to use & benefits
 *   Add operations on a bunch of classes which have different interfaces.
 *   Traverse the object structure to gather related operations
 *   Easy to add new operations.
 *   Crossing class hierarchies may break encapsulation.
 *   Related patterns include
 *     {@link Composite}, which may be applied in a visitor pattern.
 *     {@link Interpreter}, which may be used to go through structure and define new operation in a visitor pattern.
 * </pre>
 * 
 * @author hlw
 * 
 */
public class Visitor {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Cashier cashier = new Cashier();
		Goods[] goods = new Goods[] { new Goods("Kettle", 56.32, 1),
				new Goods("Towel", 7.2, 1), new Goods("Potato chips", 5.5, 3) };
		for(Goods g : goods){
			g.accept(cashier);
		}
		cashier.settle();
	}

}
