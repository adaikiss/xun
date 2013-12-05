/**
 * 
 */
package org.adaikiss.xun.concurrency.thread;

import java.util.Random;

/**
 * ThreadGroup allows us to treat the threads of a group as a single unit.
 * 
 * @author HuLingwei
 *
 */
public class ThreadGroupShowcase {
	ThreadGroup group;
	public void start(){
		group = new ThreadGroup("Search task group");
		SearchTask task = new SearchTask();
		new Thread(group, task).start();
		new Thread(group, task).start();
		new Thread(group, task).start();
		new Thread(group, task).start();
		new Thread(group, task).start();
		group.list();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new ThreadGroupShowcase().start();
		
	}

	class SearchTask implements Runnable{

		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName() + " is doing search...");
			try {
				Thread.sleep((1 + new Random().nextInt(5)) * 1000);
			} catch (InterruptedException e) {
				System.out.println(Thread.currentThread().getName() + " is interrupted!");
				return;
			}
			System.err.println(Thread.currentThread().getName() + " finished searching!");
			group.interrupt();
		}
		
	}
}
