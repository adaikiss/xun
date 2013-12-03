/**
 * 
 */
package org.adaikiss.xun.concurrency.sync;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author hlw
 *
 */
public class ConditionShowcase<E> {

	private final Lock lock = new ReentrantLock();
	private final Condition notEmpty = lock.newCondition();
	private final Condition notFull = lock.newCondition();
	private List<E> queue = new ArrayList<E>(10);

	public void add(E e) throws InterruptedException{
		try {
			lock.lock();
			while(queue.size() == 10){
				notFull.await();
			}
			queue.add(e);
			notEmpty.signal();
		} finally{
			lock.unlock();
		}
	}

	public E get() throws InterruptedException{
		try {
			lock.lock();
			while(queue.size() == 0){
				notEmpty.await();
			}
			E e = queue.remove(queue.size() - 1);
			notFull.signal();
			return e;
		} finally{
			lock.unlock();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		final ConditionShowcase<Integer> queue = new ConditionShowcase<Integer>();
		final int times = 10;
		new Thread(){
			@Override
			public void run(){
				for(int i = 0;i<times;i++){
					try {
						Thread.sleep(500);
						System.out.println("produced:" + i);
						queue.add(i);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
		Thread.sleep(500);
		new Thread(){
			@Override
			public void run(){
				for(int i = 0;i<times;i++){
					try {
						Thread.sleep(600);
						System.out.println("consumed:" + queue.get());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();

	}

}
