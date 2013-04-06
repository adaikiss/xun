/**
 * 
 */
package org.adaikiss.xun.designpattern.gof.behavioral.chainofresponsibility;

import java.util.Calendar;
import java.util.Date;

/**
 * <b>Chain of Responsibility</b>
 * 
 * <pre>
 * Definition
 *   Let more than one object handle a request without their knowing each other. Pass the request to chained objects until it has been handled.
 * Where to use & benefits
 *   One request should be handled by more than one object.
 *   Don't know which object should handle a request, probably more than one object will handle it automatically.
 *   Reduce coupling.
 *   Flexible in handling a request.
 *   Related patterns include
 *     Composite, which a chain of responsibility pattern is often applied in conjunction with.
 * </pre>
 * 
 * @author hlw
 * 
 */
public class ChainOfResponsibility {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Director director = new Director();
		Manager manager = new Manager();
		GeneralManager generalManager = new GeneralManager();
		director.setNext(manager);
		manager.setPrev(director);
		manager.setNext(generalManager);
		generalManager.setPrev(manager);
		LeavePermit leavePermit = new LeavePermit();
		leavePermit.setFrom(new Date());
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DATE, c.get(Calendar.DATE) + 1);
		leavePermit.setTo(c.getTime());
		director.handle(leavePermit);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~");
		c.set(Calendar.DATE, c.get(Calendar.DATE) + 3);
		leavePermit.setTo(c.getTime());
		director.handle(leavePermit);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~");
		c.set(Calendar.DATE, c.get(Calendar.DATE) + 4);
		leavePermit.setTo(c.getTime());
		director.handle(leavePermit);
	}

}
