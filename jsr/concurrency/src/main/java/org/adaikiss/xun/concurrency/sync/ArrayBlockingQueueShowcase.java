/**
 * 
 */
package org.adaikiss.xun.concurrency.sync;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * a fixed-sized, bounded FIFO queue
 * 
 * @author hlw
 * 
 */
public class ArrayBlockingQueueShowcase {

	/**
	 * @param args
	 */
	public static void main(String[] args){
		final ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<String>(10);
		final Random random = new Random();
		Thread producer = new Thread(){
			@Override
			public void run(){
				int times = 10;
				while(times-- > 0){
					try {
						String p = random.nextInt() + "";
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
						String c = queue.take();
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
