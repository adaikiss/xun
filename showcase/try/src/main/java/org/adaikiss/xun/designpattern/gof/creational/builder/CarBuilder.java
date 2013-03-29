/**
 * 
 */
package org.adaikiss.xun.designpattern.gof.creational.builder;

/**
 * @author hlw
 *
 */
public class CarBuilder {
	private Car car;
	public CarBuilder(Car car){
		this.car = car;
	}

	public CarBuilder buildWheels(){
		car.setProgress("building wheels...");
		return this;
	}

	public CarBuilder buildShell(){
		car.setProgress("building shell...");
		return this;
	}

	public CarBuilder buildChairs(){
		car.setProgress("building chairs...");
		return this;
	}

	public CarBuilder buildEngine(){
		car.setProgress("building engine...");
		return this;
	}
}
