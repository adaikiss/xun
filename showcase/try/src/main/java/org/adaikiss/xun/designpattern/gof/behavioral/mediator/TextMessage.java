/**
 * 
 */
package org.adaikiss.xun.designpattern.gof.behavioral.mediator;

/**
 * @author hlw
 * 
 */
public class TextMessage extends Message {
	private String content;

	public TextMessage(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return this.content;
	}
}
