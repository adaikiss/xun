/**
 * 
 */
package org.adaikiss.xun.designpattern.gof.behavioral.command;

/**
 * @author hlw
 *
 */
public class Read extends Operation {
	public Read(Operable target){
		this.description = "Read " + target;
	}
}
