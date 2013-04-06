/**
 * 
 */
package org.adaikiss.xun.designpattern.gof.creational.prototype;

/**
 * @author hlw
 * 
 */
public class HeavyGun extends Gun implements Cloneable{

	private String name;

	private float height;

	public HeavyGun() {
		super();
	}

	public HeavyGun(String name) {
		super();
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public float getHeight(){
		return this.height;
	}

	@Override
	public void setHeight(float height){
		this.height = height;
	}

	@Override
	public void attack() {
		System.out.println(name + " shoots you!");
	}

	@Override
	protected HeavyGun clone() throws CloneNotSupportedException {
		HeavyGun gun = new HeavyGun();
		gun.setName(name);
		gun.setHeight(height);
		return gun;
	}

}
