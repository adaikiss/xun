/**
 * 上午10:19:49
 */
package org.adaikiss.kay.trys.concurrency;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * hlw
 * 
 */
public class CallableTry {

	class Task implements Callable<Long> {
		private long times;
		private String name;

		public Task(long times, String name) {
			this.name = name;
			this.times = times;
		}

		@Override
		public Long call() {
			System.out.println(name + "开始执行, time[" + times + "]...");
			long before = System.currentTimeMillis();
			for (int i = 0; i < times; i++)
				;
			long after = System.currentTimeMillis();
			System.out.println(name + "执行结束.");
			long cost = after - before;
			System.out.println(name + "耗时 :" + cost);
			return cost;
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws ExecutionException,
			InterruptedException {
		long total = 0;
		CallableTry tr = new CallableTry();
		ExecutorService pool = Executors.newCachedThreadPool();
		Random rand = new Random();
		int count = 10;
		for (int i = 0; i < count; i++) {
			pool.submit(
					tr.new Task(10000000 * rand.nextInt(100), i + "任务"));
			System.out.println("next task...");
		}
		pool.shutdown();
		while (!pool.isTerminated())
			;
		System.out.println("耗时:" + total + "毫秒, 平均用时:" + total * 1.0 / count
				+ "毫秒");
	}

}
