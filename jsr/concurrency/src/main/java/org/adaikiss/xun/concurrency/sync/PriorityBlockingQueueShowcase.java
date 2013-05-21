/**
 * 
 */
package org.adaikiss.xun.concurrency.sync;

import java.util.Comparator;
import java.util.Random;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * a priority queue
 * 
 * @author hlw
 * 
 */
public class PriorityBlockingQueueShowcase {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final PriorityBlockingQueue<Integer> queue = new PriorityBlockingQueue<Integer>(10, new Comparator<Integer>(){

			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 - o2;
			}

		});
		final Random random = new Random();
		Thread producer = new Thread(){
			@Override
			public void run(){
				int times = 10;
				while(times-- > 0){
					try {
						Integer p = random.nextInt(10);
						System.out.println("produced:" + p);
						queue.put(p);
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		Thread consumer = new Thread(){
			@Override
			public void run(){
				int times = 10;
				while(times-- > 0){
					try {
						Integer c = queue.take();
						System.out.println("consumed:" + c);
						Thread.sleep(2500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		producer.start();
		consumer.start();
	}

}
