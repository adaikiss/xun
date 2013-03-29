/**
 * 
 */
package org.adaikiss.xun.designpattern.gof.behavioral.command;

import java.util.LinkedList;

/**
 * @author hlw
 * 
 */
public abstract class Operator {
	private LinkedList<Operation> operations = new LinkedList<Operation>();

	public void operate(Operation operation) {
		operations.add(operation);
	}

	public void printHistory(){
		System.out.println("~~~~~~~~~~~~~~~~~~~~~");
		for(Operation operation : operations){
			System.out.println(operation);
		}
		System.out.println("~~~~~~~~~~~~~~~~~~~~~");
	}
}
