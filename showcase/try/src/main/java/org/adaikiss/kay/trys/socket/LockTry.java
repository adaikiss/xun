/**
 * 上午09:14:27
 */
package org.adaikiss.kay.trys.socket;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * hlw
 * 
 */
public class LockTry {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LockTry tr = new LockTry();
		Account account = tr.new Account("95598036882500396", 10000);
		Lock lock = new ReentrantLock();
		ExecutorService pool = Executors.newCachedThreadPool();
		User u1 = tr.new User("小强", account, -4000d, lock);
		User u2 = tr.new User("小强他爹", account, 6000d, lock);
		User u3 = tr.new User("小强他妈", account, -8000d, lock);
		User u4 = tr.new User("小强他姐", account, 800d, lock);
		pool.execute(u1);
		pool.execute(u2);
		pool.execute(u3);
		pool.execute(u4);
		pool.shutdown();
	}

	class User implements Runnable {
		private String name;
		private Account account;
		private double ioCash;
		private Lock lock;

		public User(String name, Account account, double ioCash, Lock lock) {
			this.name = name;
			this.account = account;
			this.ioCash = ioCash;
			this.lock = lock;
		}

		public void run() {
			// 加锁
			lock.lock();
			System.out.println(name + "正在操作" + account + "账户，金额为" + ioCash
					+ "，当前金额为" + account.getCash());
			account.setCash(account.getCash() + ioCash);
			System.out.println(name + "操作" + account + "账户成功，金额为" + ioCash
					+ "，当前金额为" + account.getCash());
			// 释放锁
			lock.unlock();
		}
	}

	class Account {
		private String id;
		private double cash;

		public Account(String id, double cash) {
			this.id = id;
			this.cash = cash;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public double getCash() {
			return cash;
		}

		public void setCash(double cash) {
			this.cash = cash;
		}

		@Override
		public String toString() {
			return "Account{" + "id='" + id + '\'' + ", cash=" + cash + '}';
		}
	}
}
