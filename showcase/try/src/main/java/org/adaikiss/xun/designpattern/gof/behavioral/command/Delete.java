/**
 * 
 */
package org.adaikiss.xun.designpattern.gof.behavioral.command;

/**
 * @author hlw
 *
 */
public class Delete extends Operation {
	public Delete(Operable target){
		this.description = "Delete " + target;
	}
}
