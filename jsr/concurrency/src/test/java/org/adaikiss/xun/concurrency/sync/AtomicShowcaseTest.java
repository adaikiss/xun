/**
 * 
 */
package org.adaikiss.xun.concurrency.sync;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author hlw
 *
 */
public class AtomicShowcaseTest {

	private int count = 400;
	ExecutorService executor = Executors.newFixedThreadPool(count);
	

	@Test
	public void testNotThreadsafe() throws Exception{
		final AtomicShowcase showcase = new AtomicShowcase();
		final CountDownLatch latch = new CountDownLatch(count);
		for(int i = 0;i<count;i++){
			executor.submit(new Thread(){
				@Override
				public void run(){
					showcase.increaseA();
					latch.countDown();
				}
			});
		}
		executor.shutdown();
		latch.await();
		System.out.println("increaseA result: " + showcase.getA());
		//Result may not equals to count;
		//Assert.assertTrue(showcase.getA() != count);
	}

	@Test
	public void testThreadsafe() throws Exception{
		final AtomicShowcase showcase = new AtomicShowcase();
		final CountDownLatch latch = new CountDownLatch(count);
		for(int i = 0;i<count;i++){
			executor.submit(new Thread(){
				@Override
				public void run(){
					showcase.increaseB();
					latch.countDown();
				}
			});
		}
		executor.shutdown();
		latch.await();
		System.out.println("increaseB result: " + showcase.getB());
		Assert.assertEquals("result should equals to count!", count, showcase.getB());
	}

}
