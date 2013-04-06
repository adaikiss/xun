/**
 * 
 */
package org.adaikiss.xun.designpattern.gof.behavioral.command;

/**
 * @author hlw
 * 
 */
public abstract class Operation {
	protected String description;

	@Override
	public String toString(){
		return description;
	}
}
