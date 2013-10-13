/**
 * 上午09:26:50
 */
package org.adaikiss.kay.trys.concurrency;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * hlw
 * 
 */
public class RunnableTry {
	private long total = 0;
	public synchronized void count(long time){
		total += time;
	}
	class Task implements Runnable {
		private long times;
		private String name;
		private RunnableTry owner;

		public Task(long times, String name, RunnableTry owner) {
			this.times = times;
			this.name = name;
			this.owner = owner;
		}

		@Override
		public void run() {
			System.out.println(name + "开始执行, time[" + times + "]...");
			long before = System.currentTimeMillis();
			for (int i = 0; i < times; i++);
			long after = System.currentTimeMillis();
			System.out.println(name + "执行结束.");
			long cost = after - before;
			System.out.println(name + "耗时 :" + cost);
			owner.count(cost);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws ExecutionException,
			InterruptedException {
		long total = 0;
		RunnableTry tr = new RunnableTry();
		ExecutorService pool = Executors.newCachedThreadPool();
		Random rand = new Random();
		int count = 10;
		for (int i = 0; i < count; i++) {
			pool.execute(tr.new Task(100000 * rand.nextInt(100), i + "任务", tr));
		}
		pool.shutdown();
		while(!pool.isTerminated());
		total = tr.total;
		System.out.println("耗时:" + total + "毫秒, 平均用时:" + total * 1.0 / count + "毫秒");
	}

}
