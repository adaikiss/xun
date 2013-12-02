/**
 * 
 */
package org.adaikiss.xun.performance.chararray;

/**
 * @author HuLingwei
 *
 */
public class Stater {
	public static <T> T stat(Executable<T> task, int times){
		long[] costs = new long[times];
		long total = 0;
		long start;
		//Warm-up
		task.execute();
		T t = null;
		System.out.println("Starting " + task.getDescription() + "======>");
		for(int i = 0;i < times;i++){
			start = System.nanoTime();
			t = task.execute();
			costs[i] = System.nanoTime() - start;
			total+= costs[i];
//			System.out.println(costs[i]);
		}
		System.out.println("Total costs:" + total);
		System.out.println("Average costs:" + total/times);
		return t;
	}
}
