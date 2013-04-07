/**
 * 
 */
package org.adaikiss.xun.designpattern.gof.behavioral.command;

import org.adaikiss.xun.designpattern.gof.behavioral.memento.Memento;
import org.adaikiss.xun.designpattern.gof.structural.composite.Composite;

/**
 * <b>Command</b>
 * 
 * <pre>
 * Definition
 *   Streamlize objects by providing an interface to encapsulate a request and make the interface implemented by subclasses in order to parameterize the clients.
 * Where to use & benefits
 *   One action can be represented in many ways, like drop-down menu, buttons and popup menu.
 *   Need a callback function, i.e., register it somewhere to be called later.
 *   Specify and execute the request at different time
 *   Need to undo an action by storing its states for later retrieving.
 *   Decouple the object with its trigger
 *   Easily to be extensible by not touching the old structure.
 *   Related patterns include
 *     {@link Composite}, which aggregates an object. You may combine it into a composite command pattern. In general, a composite command is an instance of the composite.
 *     {@link Memento}, which keeps state of an object. Command supports undo and redo.
 * </pre>
 * 
 * @author hlw
 * 
 */
public class Command {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Editor editor = new Editor();
		Article article = new Article("Design Pattern");
		editor.operate(new Create(article));
		editor.operate(new Update(article));
		editor.operate(new Read(article));
		editor.operate(new Delete(article));
		editor.printHistory();
	}

}
