/**
 * 
 */
package org.adaikiss.xun.designpattern.gof.behavioral.mediator;

/**
 * @author hlw
 * 
 */
public abstract class Colleague {

	protected IMediator mediator;
	protected String name;

	public Colleague(String name, IMediator mediator) {
		this.name = name;
		this.mediator = mediator;
	}

	public abstract void receive(Message msg);

	public void send(Message msg) {
		System.out.println(name + " sent [" + msg + "]");
		mediator.send(msg, this);
	}
}
