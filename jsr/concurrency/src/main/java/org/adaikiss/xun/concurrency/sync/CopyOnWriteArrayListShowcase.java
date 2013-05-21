/**
 * 
 */
package org.adaikiss.xun.concurrency.sync;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author hlw
 * 
 */
public class CopyOnWriteArrayListShowcase {

	private static void run(final List<Integer> list,
			UncaughtExceptionHandler handler) {
		for (int i = 0; i < 10; i++) {
			list.add(i);
		}
		Thread t1 = new Thread() {
			@Override
			public void run() {
				for (Integer i : list) {
					System.out.println(i);
					list.remove(i);
				}
			}
		};
		t1.setUncaughtExceptionHandler(handler);
		t1.start();
		Thread t2 = new Thread() {
			@Override
			public void run() {
				for (Integer i : list) {
					System.out.println(i);
				}
			}
		};
		t2.setUncaughtExceptionHandler(handler);
		t2.start();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		UncaughtExceptionHandler handler = new UncaughtExceptionHandler() {

			@Override
			public void uncaughtException(Thread t, Throwable e) {
				e.printStackTrace();
			}

		};
		run(new CopyOnWriteArrayList<Integer>(), handler);
		run(new ArrayList<Integer>(), handler);
	}

}
