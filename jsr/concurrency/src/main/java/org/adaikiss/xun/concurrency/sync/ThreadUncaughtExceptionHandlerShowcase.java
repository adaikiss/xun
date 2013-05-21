/**
 * 
 */
package org.adaikiss.xun.concurrency.sync;


/**
 * @author hlw
 *
 */
public class ThreadUncaughtExceptionHandlerShowcase extends Thread{

	@Override
	public void run(){
		int i = 0;
		while(i++ < 5){
			if(i == 2){
				throw new RuntimeException("Exception!");
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Thread t = new ThreadUncaughtExceptionHandlerShowcase();
		t.setUncaughtExceptionHandler(new UncaughtExceptionHandler(){

			@Override
			public void uncaughtException(Thread t, Throwable e) {
				System.out.println(e.getMessage());
				
			}

		});
		t.start();
	}

}
