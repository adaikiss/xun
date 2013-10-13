/**
 * 
 */
package org.adaikiss.kay.trys;

/**
 * @author hlw
 *
 */
public class BooleanChangeTry {
	public static Boolean state = false;
	public static Boolean states = false;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(state.equals(states));
		Boolean state1 = state;
		state = true;
		state = false;
		System.out.println(state1.equals(state));
		Boolean state2 = state;
		state = true;
		state = false;
		System.out.println(state2.equals(state));
		Boolean state3 = state;
		state = true;
		state = false;
		System.out.println(state3.equals(state));
		Boolean state4 = state;
		state = true;
		state = false;
		System.out.println(state4.equals(state));
	}

}
