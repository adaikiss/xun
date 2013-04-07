/**
 * 
 */
package org.adaikiss.xun.designpattern.gof.behavioral.observer;

import java.util.Observable;

/**
 * @author hlw
 * 
 */
public class Magazine extends Observable {
	private String content;

	public void subscribe(Subscriber... subscribers) {
		for (Subscriber subscriber : subscribers) {
			addObserver(subscriber);
		}
	}

	public void unsubscribe(Subscriber...subscribers){
		for (Subscriber subscriber : subscribers) {
			deleteObserver(subscriber);
		}
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
		setChanged();
		notifyObservers();
	}

	@Override
	public String toString(){
		return content;
	}
}
