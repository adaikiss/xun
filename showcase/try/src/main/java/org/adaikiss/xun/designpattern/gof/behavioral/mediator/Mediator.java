/**
 * 
 */
package org.adaikiss.xun.designpattern.gof.behavioral.mediator;

import org.adaikiss.xun.designpattern.gof.behavioral.command.Command;
import org.adaikiss.xun.designpattern.gof.behavioral.observer.Observer;
import org.adaikiss.xun.designpattern.gof.structural.facade.Facade;

/**
 * <b>Mediator</b>
 * 
 * <pre>
 * Definition
 *   Define an object that encapsulates details and other objects interact with such object. The relationships are loosely decoupled.
 * Where to use & benefits
 *   Partition a system into pieces or small objects.
 *   Centralize control to manipulate participating objects(a.k.a colleagues)
 *   Clarify the complex relationship by providing a board committee.
 *   Limit subclasses.
 *   Improve objects reusabilities.
 *   Simplify object protocols.
 *   The relationship between the control class and other participating classes is multidirectional.
 *   Related patterns include
 *     {@link Facade}, which abstracts a subsystem to provide a more convenient interface, and its protocol is unidirectional, whereas a mediator enables cooperative behavior and its protocol is multidirectional.
 *     {@link Command}, which is used to coordinate functionality.
 *     {@link Observer}, which is used in mediator pattern to enhance communication.
 * </pre>
 * 
 * @author hlw
 * 
 */
public class Mediator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GameMediator mediator = new GameMediator();
		Player Jack = new Player("Jack", mediator);
		Player Jan = new Player("Jan", mediator);
		Player Calven = new Player("Calven", mediator);
		mediator.addPlayer(Jack, Jan, Calven);
		Jack.send(new TextMessage("Hello, all!"));
		Jan.send(new TextMessage("Jack is a jok!"));
		Calven.send(new TextMessage("Agree!"));
	}

}
