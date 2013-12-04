/**
 * 
 */
package org.adaikiss.xun.concurrency.thread;

import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * daemon thread is usually used as service provider.<br/>
 * jvm will stop the program and threads if no more non-daemon thread is running.
 * @author HuLingwei
 *
 */
public class DaemonShowcase {
	private Deque<Integer> queue = new ConcurrentLinkedDeque<Integer>();

	public void start(){
		Thread producer = new Thread(new Producer());
		producer.start();
		Thread consumer = new Thread(new Consumer());
		consumer.setDaemon(true);
		consumer.start();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new DaemonShowcase().start();
	}

	class Producer implements Runnable{

		@Override
		public void run() {
			for(int i = 0;i<10;i++){
				queue.add(i);
				System.out.println("produced " + i);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("producer finished!");
		}
		
	}

	class Consumer implements Runnable{

		@Override
		public void run() {
			for(;;){
				Integer i = queue.poll();
				if(null != i){
					System.err.println("consumed " + i);
				}
				try {
					//sleep longer than producer, to illustrate  that daemon thread will stop imediately while no non-daemon thread is alive.
					Thread.sleep(600);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
}
