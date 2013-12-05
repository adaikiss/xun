/**
 * 
 */
package org.adaikiss.xun.concurrency.thread;

/**
 * ThreadLocal variable won't be shared with other threads.
 * InheritableThreadLocal variable will be shared between current thread and the threads created from the current thread.
 * @author HuLingwei
 *
 */
public class ThreadLocalShowcase {

	public void start(){
		ThreadLocalTask threadLocalTask = new ThreadLocalTask();
		Thread a = new Thread(threadLocalTask);
		Thread b = new Thread(threadLocalTask);
		Thread c = new Thread(threadLocalTask);
		a.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		b.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		c.start();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ThreadLocalShowcase s = new ThreadLocalShowcase();
		s.start();
	}

	class ThreadLocalTask implements Runnable{

		private ThreadLocal<Long> value = new ThreadLocal<Long>(){

			@Override
			protected Long initialValue() {
				return System.currentTimeMillis();
			}

		};
		private boolean created = false;
		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName() + " " + value.get());
			if(!created){
				created = true;
				Thread t = new Thread(this);
				t.start();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			value.set(System.currentTimeMillis());
			System.err.println(Thread.currentThread().getName() + " " + value.get());
		}
		
	}
}
