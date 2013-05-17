/**
 * 
 */
package org.adaikiss.xun.concurrency.sync;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * As an atomic counter (incrementAndGet(), etc) that can be used by many
 * threads concurrently.</br>
 * 
 * As a primitive that supports compare-and-swap instruction (compareAndSet())
 * to implement non-blocking algorithms.
 * 
 * @author hlw
 * 
 */
public class AtomicShowcase {
	private int a = 0;
	private AtomicInteger b = new AtomicInteger(0);

	/**
	 * not threadsafe
	 */
	public void increaseA() {
		++a;
	}

	/**
	 * threadsafe
	 */
	public void increaseB() {
		b.incrementAndGet();
	}

	public int getA() {
		return a;
	}

	public int getB() {
		return b.get();
	}

}
