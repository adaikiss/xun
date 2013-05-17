/**
 * 
 */
package org.adaikiss.xun.concurrency.sync;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author hlw
 *
 */
public class SemaphoreShowcase {
	private Semaphore pool;

	public SemaphoreShowcase(int size){
		pool = new Semaphore(size);
	}

	public void acquire(){
		try {
			pool.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("washing hand...");
		try {
			Thread.sleep(new Random().nextInt(10) * 500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		pool.release();
	}

	public static void main(String[] args){
		final SemaphoreShowcase toilet = new SemaphoreShowcase(3);
		int count = 10;
		ExecutorService executor = Executors.newFixedThreadPool(count);
		for(int i = 0;i<count;i++){
			executor.submit(new Thread(){
				@Override
				public void run(){
					toilet.acquire();
				}
			});
		}
		executor.shutdown();
	}
}
