/**
 * 
 */
package org.adaikiss.xun.designpattern.gof.creational.abstractfactory;

/**
 * @author hlw
 *
 */
public class Gun extends Weapon {

	public Gun(String name){
		this.name = name;
	}

	@Override
	public void attack() {
		System.out.println(name + " shoots you!");
	}

}
