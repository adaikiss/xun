/**
 * 
 */
package org.adaikiss.xun.concurrency.sync;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * allows one thread to wait for a set of operations in other threads to complete
 * @author hlw
 *
 */
public class CountDownLatchShowcase extends Thread{

	private CountDownLatch latch;
	private String name;
	public CountDownLatchShowcase(CountDownLatch latch, String name){
		this.latch = latch;
		this.name = name;
	}

	@Override
	public void run(){
		try {
			System.out.println(name + " starting...");
			Thread.sleep(new Random().nextInt(10) * 500);
			System.out.println(name + " finished!");
			latch.countDown();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		int count = 10;
		ExecutorService executor = Executors.newFixedThreadPool(count);
		CountDownLatch latch = new CountDownLatch(count);
		for(int i = 0;i< count; i++){
			executor.submit(new CountDownLatchShowcase(latch, i + ""));
		}
		executor.shutdown();
		latch.await();
	}

}
