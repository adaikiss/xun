/**
 * 
 */
package org.adaikiss.xun.designpattern.gof.structural.facade;

/**
 * @author hlw
 *
 */
public class CustomerServiceDepartment extends Department{

	@Override
	public void talk() {
		System.out.println("Hello, this is customer service!");
	}
	
}
