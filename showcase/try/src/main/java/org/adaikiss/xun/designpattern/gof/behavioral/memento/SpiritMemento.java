/**
 * 
 */
package org.adaikiss.xun.designpattern.gof.behavioral.memento;

/**
 * @author hlw
 * 
 */
public class SpiritMemento {
	private int level;
	private double damage;
	private double armor;

	public SpiritMemento(Spirit spirit) {
		this.level = spirit.getLevel();
		this.damage = spirit.getDamage();
		this.armor = spirit.getArmor();
	}

	public void restore(Spirit spirit) {
		spirit.setLevel(level);
		spirit.setDamage(damage);
		spirit.setArmor(armor);
	}
}
