/**
 * 
 */
package org.adaikiss.xun.designpattern.gof.structural.composite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author hlw
 *
 */
public class Company {
	private String name;
	private List<Department> departments;
	public Company(String name, Department...department){
		this.name = name;
		departments = new ArrayList<Department>(Arrays.asList(department));
	}
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder().append(name).append("[");
		for(Department department : departments){
			sb.append(department.getName()).append(", ");
		}
		sb.append("]");
		return sb.toString();
	}
}
