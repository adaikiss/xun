/**
 * 上午09:53:26
 */
package org.adaikiss.kay.trys.socket;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * hlw
 * 
 */
public class ConditionTry {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ConditionTry tr = new ConditionTry();
		//创建并发访问的账户
        MyCount myCount = tr.new MyCount("95599200901215522", 10000);
        //创建一个线程池
        ExecutorService pool = Executors.newFixedThreadPool(2);
        Thread t1 = tr.new SaveThread("张三", myCount, 2000);
        Thread t2 = tr.new SaveThread("李四", myCount, 3600);
        Thread t3 = tr.new DrawThread("王五", myCount, 2700);
        Thread t4 = tr.new SaveThread("老张", myCount, 600);
        Thread t5 = tr.new DrawThread("老牛", myCount, 1300);
        Thread t6 = tr.new DrawThread("胖子", myCount, 800);
        //执行各个线程
        pool.execute(t1);
        pool.execute(t2);
        pool.execute(t3);
        pool.execute(t4);
        pool.execute(t5);
        pool.execute(t6);
        //关闭线程池
        pool.shutdown(); 
	}

	class DrawThread extends Thread {
		private String name; // 操作人
		private MyCount myCount; // 账户
		private int x; // 存款金额

		DrawThread(String name, MyCount myCount, int x) {
			this.name = name;
			this.myCount = myCount;
			this.x = x;
		}

		public void run() {
			myCount.drawing(x, name);
		}
	}

	class SaveThread extends Thread {
		private String name; // 操作人
		private MyCount myCount; // 账户
		private int x; // 存款金额

		SaveThread(String name, MyCount myCount, int x) {
			this.name = name;
			this.myCount = myCount;
			this.x = x;
		}

		public void run() {
			myCount.saving(x, name);
		}
	}

	class MyCount {
		private String oid; // 账号
		private int cash; // 账户余额
		private Lock lock = new ReentrantLock(); // 账户锁
		//private Condition _save = lock.newCondition(); // 存款条件
		private Condition _draw = lock.newCondition(); // 取款条件

		MyCount(String oid, int cash) {
			this.oid = oid;
			this.cash = cash;
		}

		/**
		 * 存款
		 * 
		 * @param x
		 *            操作金额
		 * @param name
		 *            操作人
		 */
		public void saving(int x, String name) {
			lock.lock(); // 获取锁
			if (x > 0) {
				cash += x; // 存款
				System.out.println(name + "存款" + x + "，当前余额为" + cash);
			}
			_draw.signalAll(); // 唤醒所有等待线程。
			lock.unlock(); // 释放锁
		}

		/**
		 * 取款
		 * 
		 * @param x
		 *            操作金额
		 * @param name
		 *            操作人
		 */
		public void drawing(int x, String name) {
			lock.lock(); // 获取锁
			try {
				if (cash - x < 0) {
					_draw.await(); // 阻塞取款操作
				} else {
					cash -= x; // 取款
					System.out.println(name + "取款" + x + "，当前余额为" + cash);
				}
				//_save.signalAll(); // 唤醒所有存款操作
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.unlock(); // 释放锁
			}
		}

		public String getOid() {
			return oid;
		}

		public void setOid(String oid) {
			this.oid = oid;
		}
	}
}
