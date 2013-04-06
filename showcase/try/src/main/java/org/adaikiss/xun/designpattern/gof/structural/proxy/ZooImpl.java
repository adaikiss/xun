/**
 * 
 */
package org.adaikiss.xun.designpattern.gof.structural.proxy;

/**
 * @author hlw
 * 
 */
public class ZooImpl implements Zoo{
	private String name;

	public ZooImpl(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void visit(String visitor) {
		System.out.println(visitor + " visit " + name);
	}
}
