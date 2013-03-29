/**
 * 
 */
package org.adaikiss.xun.designpattern.gof.creational.abstractfactory;

/**
 * @author hlw
 *
 */
public class Knife extends Weapon {

	public Knife(String name){
		this.name = name;
	}

	@Override
	public void attack() {
		System.out.println(name + " knife stabs you!");
	}

}
