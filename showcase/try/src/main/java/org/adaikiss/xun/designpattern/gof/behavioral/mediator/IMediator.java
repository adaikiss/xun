/**
 * 
 */
package org.adaikiss.xun.designpattern.gof.behavioral.mediator;

/**
 * @author hlw
 *
 */
public interface IMediator {
	void send(Message msg, Colleague colleague);
}
