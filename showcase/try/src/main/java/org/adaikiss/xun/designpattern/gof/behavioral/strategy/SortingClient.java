/**
 * 
 */
package org.adaikiss.xun.designpattern.gof.behavioral.strategy;

/**
 * @author hlw
 * 
 */
public class SortingClient {
	private SortingStrategy strategy;

	public void setStrategy(SortingStrategy strategy) {
		this.strategy = strategy;
	}

	public void sort(int[] array) {
		System.out.println("original:");
		print(array);
		print(this.strategy.sort(array));
	}

	private static void print(int[] array){
		for(int o : array){
			System.out.print(o);
			System.out.print(" ");
		}
		System.out.println();
	}
}
