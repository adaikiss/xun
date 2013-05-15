/**
 * 
 */
package org.adaikiss.xun.concurrency.sync;

/**
 * @author hlw
 *
 */
public class SynchronizedShowcase {
	/**
	 * synchronized
	 */
	public synchronized void do1(int time){
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * synchronized
	 */
	public synchronized void do2(int time){
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * normal
	 */
	public void do3(int time){
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * normal
	 */
	public void do4(int time){
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * static normal
	 */
	public static void do5(int time){
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * static synchronized
	 */
	public static synchronized void do6(int time){
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * static synchronized
	 */
	public static synchronized void do7(int time){
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * synchronized block
	 * @param time
	 */
	public void do8(int time){
		synchronized(this){
			try {
				Thread.sleep(time);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args){
	}
}