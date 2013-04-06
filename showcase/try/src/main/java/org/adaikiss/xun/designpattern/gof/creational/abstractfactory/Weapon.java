/**
 * 
 */
package org.adaikiss.xun.designpattern.gof.creational.abstractfactory;

/**
 * @author hlw
 *
 */
public abstract class Weapon {
	enum Location{
		USA,
		Russian
	}
	protected String name;

	public abstract void attack();
}
