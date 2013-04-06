/**
 * 
 */
package org.adaikiss.xun.designpattern.gof.behavioral.command;

/**
 * @author hlw
 *
 */
public class Update extends Operation {
	public Update(Operable target){
		this.description = "Update " + target;
	}
}
