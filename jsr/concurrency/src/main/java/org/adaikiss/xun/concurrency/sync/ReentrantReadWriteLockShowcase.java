/**
 * 
 */
package org.adaikiss.xun.concurrency.sync;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author HuLingwei
 *
 */
public class ReentrantReadWriteLockShowcase {

	private ReadWriteLock lock;
	private int value = 0;
	public ReentrantReadWriteLockShowcase(){
		lock = new ReentrantReadWriteLock();
	}

	public void start(){
		Thread reader1 = new Reader("reader1");
		Thread reader2 = new Reader("reader2");
		Thread reader3 = new Reader("reader3");
		Thread writer = new Writer("writer");
		reader1.start();
		reader2.start();
		reader3.start();
		writer.start();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new ReentrantReadWriteLockShowcase().start();

	}

	class Writer extends Thread{

		public Writer(String name) {
			super(name);
		}

		@Override
		public void run() {
			for(int i = 0;i<20;i++){
				lock.writeLock().lock();
				System.out.println("Thread-" + getName() + " attempt to modify value.");
				value = value + 1;
				System.out.println("Thread-" + getName() + " has modified the value.");
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				lock.writeLock().unlock();
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	class Reader extends Thread{

		public Reader(String name) {
			super(name);
		}

		@Override
		public void run() {
			for(int i = 0;i<10;i++){
				if(lock.readLock().tryLock()){
					System.out.println("Thread-" + getName() + ": value is " + value);
					lock.readLock().unlock();
				}else{
					System.out.println("Thread-" + getName() + ":value is locked by  another thread!");
				}
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
