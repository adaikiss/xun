/**
 * 上午08:57:26
 */
package org.adaikiss.kay.trys.socket;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * hlw
 * 
 */
public class CallableTry {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws ExecutionException, InterruptedException{
		ExecutorService pool = Executors.newFixedThreadPool(2);
		Callable<String> c1 = new MyCallable("A");
		Callable<String> c2 = new MyCallable("B");
		Future<String> f1 = pool.submit(c1);
		Future<String> f2 = pool.submit(c2);
		System.out.println(">>>" + f1.get());
		System.out.println(">>>" + f2.get());
		pool.shutdown();
	}

	static class MyCallable implements Callable<String> {
		private String id;

		public MyCallable(String id) {
			this.id = id;
		}

		@Override
		public String call() throws Exception {
			return id + "任务返回内容.";
		}

	}
}
