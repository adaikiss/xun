/**
 * 
 */
package org.adaikiss.kay.trys.concurrency;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Stack;

/**
 * @author hlw
 *
 */
public class LockTry {

	private static final Object LOCK = new Object[0];
	private static final Map<Long, Integer> data = new HashMap<Long, Integer>();
	private static final Stack<Long> ids = new Stack<Long>();
	private static final int TASK_COUNT = 20;
	private static final int TIMES = 30;
	private static final List<Thread> tasks = new ArrayList<Thread>();
	private static void prepare(){
		for(int i = 0; i < TIMES; i ++){
			ids.push(100000l + i);
		}
	}

	public static void test() throws Exception{
		prepare();
		final List<List<Long>> results = new ArrayList<List<Long>>();
		Thread th = new Thread(){
			@Override
			public void run(){
				for(int i = 0; i < TASK_COUNT; i ++){
					try {
						Thread.sleep(new Random().nextInt(1000) + 500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					Thread t = new Thread(){
						@Override
						public void run() {
							List<Long> list = new ArrayList<Long>();
							synchronized(LOCK){
								Map<Long, Integer> map = new HashMap<Long, Integer>(data);
								try {
									Thread.sleep(new Random().nextInt(1000) + 1000);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								for(Map.Entry<Long, Integer> entry : map.entrySet()){
									if(entry.getValue().equals(1)){
										list.add(entry.getKey());
										data.put(entry.getKey(), 2);
									}
								}
								results.add(list);
							}
						}
					};
					t.start();
					tasks.add(t);
				}
			}
		};
		Thread thread = new Thread(){
			@Override
			public void run(){
				while(!ids.isEmpty()){
					data.put(ids.pop(), 1);
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		thread.start();
		th.start();
		thread.join();
		th.join();
		for(Thread t : tasks){
			t.join();
		}
		for(List<Long> result : results){
			for(Long l : result){
				System.out.print(l);
				System.out.print(", ");
			}
			System.out.println();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		test();
	}

}
