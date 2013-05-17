/**
 * 
 */
package org.adaikiss.xun.concurrency.sync;

/**
 * If you have a thread running loop() and another thread calling stop(), you
 * might run into an infinite loop if you omit "volatile", since the first
 * thread might cache the value of stop.</br>
 * 
 * <pre>
 * volatile int i = 0;
 * void incIBy5() {
 *     i += 5;
 * }
 * 
 * the code will be compiled like this (except you cannot synchronize on int):
 * 
 * void incIBy5() {
 *     int temp;
 *     synchronized(i) { temp = i }
 *     synchronized(i) { i = temp + 5 }
 * }
 * </pre>
 * 
 * @author hlw
 * 
 */
public class VolatileShowcase extends Thread {

	private volatile boolean stop = false;

	@Override
	public void run() {
		while (!stop) {
			System.out.println("...");
		}
	}

	public void shutdown() {
		stop = true;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final VolatileShowcase o = new VolatileShowcase();
		o.start();
		new Thread() {
			@Override
			public void run() {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				o.shutdown();
			}
		}.start();
	}
}
