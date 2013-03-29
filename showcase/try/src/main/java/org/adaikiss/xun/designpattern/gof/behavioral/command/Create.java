/**
 * 
 */
package org.adaikiss.xun.designpattern.gof.behavioral.command;

/**
 * @author hlw
 *
 */
public class Create extends Operation {
	public Create(Operable target){
		this.description = "Create " + target;
	}
}
