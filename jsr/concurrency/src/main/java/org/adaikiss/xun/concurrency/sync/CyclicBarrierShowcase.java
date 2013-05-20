/**
 * 
 */
package org.adaikiss.xun.concurrency.sync;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * allows multiple threads to wait until they all reach a common barrier point
 * @author hlw
 *
 */
public class CyclicBarrierShowcase extends Thread{

	private CyclicBarrier barrier;
	private int[] times;
	private String[] work;
	private String name;

	public CyclicBarrierShowcase(String name, CyclicBarrier barrier, int[] times, String[] work){
		this.name = name;
		this.barrier = barrier;
		this.times = times;
		this.work = work;
	}

	@Override
	public void run(){
		for(int i = 0;i< times.length;i++){
			try {
				Thread.sleep(times[i] * 1000);
				System.out.println(name + " finished " + work[i]);
				barrier.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(3);
		CyclicBarrier barrier = new CyclicBarrier(3);
		String[] works = new String[]{"A", "B", "C", "D"};
		CyclicBarrierShowcase jack = new CyclicBarrierShowcase("Jack", barrier, new int[]{1, 4,2,5}, works);
		CyclicBarrierShowcase john = new CyclicBarrierShowcase("John", barrier, new int[]{2, 5,3,1}, works);
		CyclicBarrierShowcase jetty = new CyclicBarrierShowcase("Jetty", barrier, new int[]{5, 0,2,0}, works);
		executor.submit(jack);
		executor.submit(john);
		executor.submit(jetty);
		executor.shutdown();
	}

}
