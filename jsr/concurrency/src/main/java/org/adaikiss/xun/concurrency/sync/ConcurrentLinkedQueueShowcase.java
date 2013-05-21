/**
 * 
 */
package org.adaikiss.xun.concurrency.sync;

import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * an unbounded non-blocking FIFO queue
 * 
 * @author hlw
 * 
 */
public class ConcurrentLinkedQueueShowcase {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<String>();
		final Random random = new Random();
		Thread producer = new Thread(){
			@Override
			public void run(){
				int times = 10;
				while(times-- > 0){
					try {
						String p = random.nextInt() + "";
						System.out.println("produced:" + p);
						queue.add(p);
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
				while(times > 0){
					try {
						Thread.sleep(500);
						String c = queue.poll();
						if(null == c){
							System.out.println("no production, acquire again...");
							continue;
						}
						times--;
						System.out.println("consumed:" + c);
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
