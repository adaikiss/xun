/**
 * 
 */
package org.adaikiss.xun.designpattern.gof.behavioral.chainofresponsibility;

/**
 * @author hlw
 * 
 */
public abstract class LeavePermitRight {
	protected LeavePermitRight prev;
	protected LeavePermitRight next;

	public LeavePermitRight getPrev() {
		return prev;
	}

	public void setPrev(LeavePermitRight prev) {
		this.prev = prev;
	}

	public LeavePermitRight getNext() {
		return this.next;
	}

	public void setNext(LeavePermitRight next) {
		this.next = next;
	}

	abstract void handle(LeavePermit permit);

	protected void handOn(LeavePermit permit) {
		if (this.next != null) {
			this.next.handle(permit);
		}
	}
}
