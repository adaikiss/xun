/**
 * 
 */
package org.adaikiss.xun.designpattern.gof.behavioral.chainofresponsibility;

import java.util.Date;

/**
 * @author hlw
 *
 */
public class LeavePermit {
	private Date from;
	private Date to;
	private String reason;
	private String name;

	private boolean permitted;

	public Date getFrom() {
		return from;
	}

	public void setFrom(Date from) {
		this.from = from;
	}

	public Date getTo() {
		return to;
	}

	public void setTo(Date to) {
		this.to = to;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isPermitted() {
		return permitted;
	}

	public void setPermitted(boolean permitted) {
		this.permitted = permitted;
	}

	public double getPeriod(){
		return (to.getTime() - from.getTime())/(1000 * 3600 * 24.0);
	}
}
