/**
 * 
 */
package org.adaikiss.xun.concurrency.thread;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.Random;
import java.util.concurrent.ThreadFactory;

/**
 * @author HuLingwei
 *
 */
public class ThreadFactoryShowcase {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ThreadFactoryShowcase s = new ThreadFactoryShowcase();
		MyThreadFactory f = s.new MyThreadFactory(5);
		Task t = s.new Task();
		try {
			for(int i = 0;i<6;i++){
				f.newThread(t).start();
			}
		} catch (Exception e) {
			System.err.println("Exception occurred:" + e.getMessage());
		}
		System.out.println("Active threads:" + f.activeCount());
	}

	class Task implements Runnable{

		@Override
		public void run() {
			try {
				Thread.sleep((new Random().nextInt(5) + 1) * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				return;
			}
			System.out.println(Thread.currentThread().getName() + " finished!");
		}
	}

	class MyThreadFactory implements ThreadFactory{
		private int counter;
		private int maxCount;
		private ThreadGroup group;
		private UncaughtExceptionHandler uncaughtExceptionHandler;

		public MyThreadFactory(int count){
			maxCount = count;
			group = new ThreadGroup("MyThreadGroup");
			uncaughtExceptionHandler = new UncaughtExceptionHandler(){

				@Override
				public void uncaughtException(Thread t, Throwable e) {
					System.out.println(t.getName() + " threw uncaught exception:" + e.getMessage());
				}
				
			};
		}

		@Override
		public Thread newThread(Runnable r) {
			if(counter >= maxCount){
				throw new RuntimeException("can't create any more thread!");
			}
			Thread t = new Thread(group, r);
			t.setUncaughtExceptionHandler(uncaughtExceptionHandler);
			counter++;
			return t;
		}

		public int getCreatedThreadNum(){
			return counter;
		}

		public int activeCount(){
			return group.activeCount();
		}
	}
}
