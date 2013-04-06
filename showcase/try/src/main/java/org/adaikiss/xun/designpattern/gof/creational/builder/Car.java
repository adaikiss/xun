/**
 * 
 */
package org.adaikiss.xun.designpattern.gof.creational.builder;

/**
 * @author hlw
 * 
 */
public class Car {
	private String progress;

	public String getProgress() {
		return progress;
	}

	public void setProgress(String progress) {
		this.progress = progress;
		System.out.println(progress);
	}

	public Car() {
		new CarBuilder(this).buildShell().buildWheels().buildEngine()
				.buildChairs();
	}
}
