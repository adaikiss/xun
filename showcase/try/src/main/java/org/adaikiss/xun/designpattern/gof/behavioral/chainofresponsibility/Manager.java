/**
 * 
 */
package org.adaikiss.xun.designpattern.gof.behavioral.chainofresponsibility;

/**
 * @author hlw
 * 
 */
public class Manager extends LeavePermitRight {

	@Override
	public void handle(LeavePermit permit) {
		permit.setPermitted(true);
		System.out.println("Manager permits your leave!");
		//if not permit, just return;
		if(permit.getPeriod() > 5){
			System.out.println("Period is more than 5 days, need General Manager permission!");
			handOn(permit);
		}
	}

}
