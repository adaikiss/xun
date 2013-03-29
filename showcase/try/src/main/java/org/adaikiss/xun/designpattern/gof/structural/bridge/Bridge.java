/**
 * 
 */
package org.adaikiss.xun.designpattern.gof.structural.bridge;

import java.util.Date;

import org.adaikiss.xun.designpattern.gof.creational.abstractfactory.AbstractFactory;
import org.adaikiss.xun.designpattern.gof.structural.adapter.Adapter;

/**
 * <b>Bridge</b>
 * 
 * <pre>
 * Definition
 *   Decouple an abstraction or interface from its implementation so that the two can vary independently.
 * Where to use & benefits
 *   Want to separate abstraction and implementation permanently
 *   Share an implementation among multiple objects
 *   Want to improve extensibility
 *   Hide implementation details from clients
 *   Related patterns include
 *     {@link AbstractFactory}, which can be used to create and configure a particular bridge.
 *     {@link Adapter}, which makes unrelated classes work together, whereas a bridge makes a clear-cut between abstraction and implementation.
 * </pre>
 * 
 * @author hlw
 * 
 */
public class Bridge {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MailBox mailBox = new SinaMailBox(new SinaMailProvider());
		mailBox.sendMail(new Mail("Missing your sister",
				"Hi, how are your sister?", new Date()));
		mailBox.readMail();
	}

}
