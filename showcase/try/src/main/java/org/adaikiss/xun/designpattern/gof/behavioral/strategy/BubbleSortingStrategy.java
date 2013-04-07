/**
 * 
 */
package org.adaikiss.xun.designpattern.gof.behavioral.strategy;

/**
 * @author hlw
 *
 */
public class BubbleSortingStrategy implements SortingStrategy{

	@Override
	public int[] sort(int[] array) {
		System.out.println("starting bubble sorting...");
		for(int i = 0;i < array.length - 1;i++){
			for(int j = i+1;j<array.length;j++){
				if(array[j] < array[i]){
					int tmp = array[j];
					array[j] = array[i];
					array[i] = tmp;
				}
			}
		}
		return array;
	}
	
}
