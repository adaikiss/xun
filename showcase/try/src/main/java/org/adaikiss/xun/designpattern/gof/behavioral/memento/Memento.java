/**
 * 
 */
package org.adaikiss.xun.designpattern.gof.behavioral.memento;

import org.adaikiss.xun.designpattern.gof.behavioral.command.Command;
import org.adaikiss.xun.designpattern.gof.behavioral.iterator.Iterator;

/**
 * <b>Memento</b>
 * 
 * <pre>
 * Definition
 *   To record an object internal state without violating encapsulation and reclaim it later without knowledge of the original object.
 * Where to use & benefits
 *   Let some info in an object to be available by another object by using default access control.
 *   Save some info for later uses.
 *   Need undo/redo features.
 *   Used in database transaction.
 *   Related patterns include
 *     {@link Command}, which supports undo or redo features, whereas a memento keeps state of an object.
 *     {@link Iterator}, which provides a way to access the elements of an aggregate object sequentially without exposing its underlying representation, whereas a memento can be used for iteration.
 * </pre>
 * 
 * @author hlw
 * 
 */
public class Memento {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Spirit tiny = new Spirit("Tiny");
		System.out.println(tiny);
		tiny.levelUp();
		SpiritMemento level2 = tiny.save();
		System.out.println(tiny);
		tiny.levelUp();
		System.out.println(tiny);
		tiny.levelUp();
		System.out.println(tiny);
		tiny.restore(level2);
		System.out.println(tiny);
	}

}
