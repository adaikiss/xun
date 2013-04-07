/**
 * 
 */
package org.adaikiss.xun.designpattern.gof.behavioral.memento;

/**
 * @author hlw
 *
 */
public class Spirit {
	private String name;
	private int level = 1;
	private double damage = 56;
	private double armor = 0;
	private int growthRate = 10;

	public void levelUp(){
		level++;
		damage += growthRate * 60/100.00;
		armor += growthRate * 8/100.00;
	}

	public Spirit(String name) {
		super();
		this.name = name;
	}

	public SpiritMemento save(){
		return new SpiritMemento(this);
	}

	public void restore(SpiritMemento memento){
		memento.restore(this);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public double getDamage() {
		return damage;
	}

	public void setDamage(double damage) {
		this.damage = damage;
	}

	public double getArmor() {
		return armor;
	}

	public void setArmor(double armor) {
		this.armor = armor;
	}

	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("name:").append(name).append(", level:").append(level).append(", damage:").append(damage).append(", armor:").append(armor).append(", growth rate:").append(growthRate);
		return sb.toString();
	}
}
