/**
 * 2011-2-22
 */
package org.adaikiss.kay.trys.algorithm;

/**
 * hlw
 *
 */
public class OrderTry {

	public static void bubble(int[] array){
		for(int i = 0;i < array.length - 1;i++){
			for(int j = i; j < array.length;j++){
				if(array[i] > array[j]){
					int tmp = array[i];
					array[i] = array[j];
					array[j] = tmp;
				}
			}
		}
	}

	public static void out(int[] array){
		for(int i:array){
			System.out.print(i);
			System.out.print(", ");
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] aa = {1, 23, 4, 28, 12, 88, 63, 77, 19, 41, 22, 56, 99, 3};
		bubble(aa);
		out(aa);
	}

}
