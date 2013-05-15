/**
 * 
 */
package org.adaikiss.xun.concurrency.sync;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author hlw
 *
 */
public class SynchronizedIllustrationTest {

	ExecutorService executor = Executors.newFixedThreadPool(2);
	int time = 500;
	int most = 600;
	int least = 999;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void testNormal() throws Exception{
		final SynchronizedShowcase showcase = new SynchronizedShowcase();
		final CountDownLatch latch = new CountDownLatch(2);
		Thread a = new Thread(){
			@Override
			public void run(){
				showcase.do3(time);
				latch.countDown();
			}
		};
		Thread b = new Thread(){
			@Override
			public void run(){
				showcase.do4(time);
				latch.countDown();
			}
		};
		long start = System.currentTimeMillis();
		executor.submit(a);
		executor.submit(b);
		executor.shutdown();
		latch.await();
		long cost = System.currentTimeMillis() - start;
		System.out.println("testNormal total cost:" + cost);
		Assert.assertTrue("total cost should less than " + most + "ms", cost < most);
	}

	/**
	 * a synchronized method blocks synchronized methods in same object
	 * @throws Exception
	 */
	@Test
	public void testSynchronized() throws Exception{
		final SynchronizedShowcase showcase = new SynchronizedShowcase();
		final CountDownLatch latch = new CountDownLatch(2);
		Thread a = new Thread(){
			@Override
			public void run(){
				showcase.do1(time);
				latch.countDown();
			}
		};
		Thread b = new Thread(){
			@Override
			public void run(){
				showcase.do2(time);
				latch.countDown();
			}
		};
		long start = System.currentTimeMillis();
		executor.submit(a);
		executor.submit(b);
		executor.shutdown();
		latch.await();
		long cost = System.currentTimeMillis() - start;
		System.out.println("testSynchronized total cost:" + cost);
		Assert.assertTrue("total cost should greater than " + least + "ms", cost > least);
	}

	/**
	 * a synchronized method does not block methods not synchronized 
	 * @throws Exception
	 */
	@Test
	public void testSynchronizedAndNormal() throws Exception{
		final SynchronizedShowcase showcase = new SynchronizedShowcase();
		final CountDownLatch latch = new CountDownLatch(2);
		Thread a = new Thread(){
			@Override
			public void run(){
				showcase.do1(time);
				latch.countDown();
			}
		};
		Thread b = new Thread(){
			@Override
			public void run(){
				showcase.do3(time);
				latch.countDown();
			}
		};
		long start = System.currentTimeMillis();
		executor.submit(a);
		executor.submit(b);
		executor.shutdown();
		latch.await();
		long cost = System.currentTimeMillis() - start;
		System.out.println("testSynchronizedAndNormal total cost:" + cost);
		Assert.assertTrue("total cost should less than " + most + "ms", cost < most);
	}

	/**
	 * a synchronized block blocks any operation on the synchronized object
	 * @throws Exception
	 */
	@Test
	public void testSynchronizedBlockAndNormal() throws Exception{
		final SynchronizedShowcase showcase = new SynchronizedShowcase();
		final CountDownLatch latch = new CountDownLatch(2);
		Thread a = new Thread(){
			@Override
			public void run(){
				showcase.do8(time);
				latch.countDown();
			}
		};
		Thread b = new Thread(){
			@Override
			public void run(){
				showcase.do3(time);
				latch.countDown();
			}
		};
		long start = System.currentTimeMillis();
		executor.submit(a);
		executor.submit(b);
		executor.shutdown();
		latch.await();
		long cost = System.currentTimeMillis() - start;
		System.out.println("testSynchronizedBlockAndNormal total cost:" + cost);
		Assert.assertTrue("total cost should greater than " + least + "ms", cost < least);
	}

	/**
	 * a static synchronized method does not block static methods not synchronized
	 * @throws Exception
	 */
	@Test
	public void testSynchronizedStaticAndStatic() throws Exception{
		final CountDownLatch latch = new CountDownLatch(2);
		Thread a = new Thread(){
			@Override
			public void run(){
				SynchronizedShowcase.do5(time);
				latch.countDown();
			}
		};
		Thread b = new Thread(){
			@Override
			public void run(){
				SynchronizedShowcase.do6(time);
				latch.countDown();
			}
		};
		long start = System.currentTimeMillis();
		executor.submit(a);
		executor.submit(b);
		executor.shutdown();
		latch.await();
		long cost = System.currentTimeMillis() - start;
		System.out.println("testSynchronizedStaticAndStatic total cost:" + cost);
		Assert.assertTrue("total cost should less than " + most + "ms", cost < most);
	}
	
	/**
	 * a synchronized static method does not block synchronized methods not static
	 * @throws Exception
	 */
	@Test
	public void testSynchronizedStaticAndSynchronized() throws Exception{
		final SynchronizedShowcase showcase = new SynchronizedShowcase();
		final CountDownLatch latch = new CountDownLatch(2);
		Thread a = new Thread(){
			@Override
			public void run(){
				showcase.do1(time);
				latch.countDown();
			}
		};
		Thread b = new Thread(){
			@Override
			public void run(){
				SynchronizedShowcase.do6(time);
				latch.countDown();
			}
		};
		long start = System.currentTimeMillis();
		executor.submit(a);
		executor.submit(b);
		executor.shutdown();
		latch.await();
		long cost = System.currentTimeMillis() - start;
		System.out.println("testSynchronizedStaticAndSynchronized total cost:" + cost);
		Assert.assertTrue("total cost should less than " + most + "ms", cost < most);
	}
	
	/**
	 * a synchronized static method blocks synchronized static methods in same class
	 * @throws Exception
	 */
	@Test
	public void testSynchronizedStatic() throws Exception{
		final CountDownLatch latch = new CountDownLatch(2);
		Thread a = new Thread(){
			@Override
			public void run(){
				SynchronizedShowcase.do7(time);
				latch.countDown();
			}
		};
		Thread b = new Thread(){
			@Override
			public void run(){
				SynchronizedShowcase.do6(time);
				latch.countDown();
			}
		};
		long start = System.currentTimeMillis();
		executor.submit(a);
		executor.submit(b);
		executor.shutdown();
		latch.await();
		long cost = System.currentTimeMillis() - start;
		System.out.println("testSynchronizedStatic total cost:" + cost);
		Assert.assertTrue("total cost should greater than " + least + "ms", cost > least);
	}
}
