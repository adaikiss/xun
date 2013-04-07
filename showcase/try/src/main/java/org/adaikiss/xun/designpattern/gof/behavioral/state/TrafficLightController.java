/**
 * 
 */
package org.adaikiss.xun.designpattern.gof.behavioral.state;

import org.adaikiss.xun.designpattern.gof.behavioral.state.TrafficLight.Color;

/**
 * @author hlw
 * 
 */
public class TrafficLightController {
	private TrafficLight state;
	private int turn;

	public TrafficLightController() {
		state = new TrafficLight(Color.Green);
	}

	public void shift() {
		switch(turn++%4){
		case 0:
			state.green();
			turn = 1;
			break;
		case 1:
			state.yellow();
			break;
		case 2:
			state.red();
			break;
		case 3:
			state.yellow();
			break;
		}
	}

	public void show(){
		System.out.println(state);
	}
}
