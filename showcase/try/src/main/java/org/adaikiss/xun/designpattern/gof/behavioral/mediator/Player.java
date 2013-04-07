/**
 * 
 */
package org.adaikiss.xun.designpattern.gof.behavioral.mediator;

/**
 * @author hlw
 *
 */
public class Player extends Colleague {

	public Player(String name, IMediator mediator){
		super(name, mediator);
	}

	@Override
	public void receive(Message msg) {
		System.out.println(name + " received [" + msg + "]");
	}

}
