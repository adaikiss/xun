/**
 * 
 */
package org.adaikiss.xun.designpattern.gof.behavioral.chainofresponsibility;

/**
 * @author hlw
 *
 */
public class Director extends LeavePermitRight {

	@Override
	void handle(LeavePermit permit) {
		permit.setPermitted(true);
		System.out.println("Director permits your leave!");
		//if not permit, just return;
		if(permit.getPeriod() > 3){
			System.out.println("Period is more than 3 days, need Manager permission!");
			handOn(permit);
		}
	}

}
