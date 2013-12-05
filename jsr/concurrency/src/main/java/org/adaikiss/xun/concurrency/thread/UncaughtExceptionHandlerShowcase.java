/**
 * 
 */
package org.adaikiss.xun.concurrency.thread;

import java.lang.Thread.UncaughtExceptionHandler;

/**
 * @author HuLingwei
 *
 */
public class UncaughtExceptionHandlerShowcase {

	public void start(){
		ExceptionThread t = new ExceptionThread("a");
		t.setUncaughtExceptionHandler(new UncaughtExceptionHandler(){

			@Override
			public void uncaughtException(Thread t, Throwable e) {
				System.out.println("Thread-" + t.getName() + " threw an exception:" + e.getMessage());
			}
			
		});
		t.start();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new UncaughtExceptionHandlerShowcase().start();
	}

	class ExceptionThread extends Thread{
		private boolean exception = true;

		public ExceptionThread(String name) {
			super(name);
		}

		@Override
		public void run() {
			System.out.println("Thread-" + getName() + " is doing something...");
			if(exception){
				throw new RuntimeException("something goes wrong!");
			}
			System.out.println("Thread-" + getName() + " finished doing something.");
		}
		
	}
}
