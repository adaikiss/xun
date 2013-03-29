/**
 * 
 */
package org.adaikiss.xun.designpattern.gof.behavioral.chainofresponsibility;

/**
 * @author hlw
 *
 */
public class GeneralManager extends LeavePermitRight {

	@Override
	void handle(LeavePermit permit) {
		permit.setPermitted(true);
		System.out.println("General Manager permits your leave!");
		//if not permit, just return;
		handOn(permit);
	}

}
