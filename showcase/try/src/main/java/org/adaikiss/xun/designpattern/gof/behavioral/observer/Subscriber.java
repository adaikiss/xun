/**
 * 
 */
package org.adaikiss.xun.designpattern.gof.behavioral.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * @author hlw
 * 
 */
public class Subscriber implements Observer {

	private String name;

	public Subscriber(String name) {
		this.name = name;
	}

	@Override
	public void update(Observable o, Object arg) {
		System.out.println(name + " received [" + o + "]");
	}

}
