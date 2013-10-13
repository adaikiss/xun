/**
 * 上午09:30:35
 */
package org.adaikiss.kay.trys.socket;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * hlw
 * 
 */
public class ReadWriteLockTry {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ReadWriteLockTry tr = new ReadWriteLockTry();
		Account account = tr.new Account("95598036882500396", 10000);
		ReadWriteLock lock = new ReentrantReadWriteLock(false);
		ExecutorService pool = Executors.newCachedThreadPool();
		User u1 = tr.new User("小强", account, -4000d, lock, false);
		User u2 = tr.new User("小强他爹", account, 6000d, lock, false);
		User u3 = tr.new User("小强他妈", account, -8000d, lock, false);
		User u4 = tr.new User("小强他姐", account, 800d, lock, false);
		User u5 = tr.new User("小强还没出生的妹", account, 0d, lock, true);
		pool.execute(u1);
		pool.execute(u2);
		pool.execute(u3);
		pool.execute(u4);
		pool.execute(u5);
		pool.shutdown();
	}

	class User implements Runnable {
		private String name;
		private Account account;
		private double ioCash;
		private ReadWriteLock lock;
		private boolean isCheck;

		public User(String name, Account account, double ioCash,
				ReadWriteLock lock, boolean isCheck) {
			this.name = name;
			this.account = account;
			this.ioCash = ioCash;
			this.lock = lock;
			this.isCheck = isCheck;
		}

		public void run() {
			if (isCheck) {
				// 获取读锁
				lock.readLock().lock();
				System.out.println("读：" + name + "正在查询" + account + "账户，当前金额为"
						+ account.getCash());
				// 释放读锁
				lock.readLock().unlock();
			} else {
				// 获取写锁
				lock.writeLock().lock();
				// 执行现金业务
				System.out.println("写：" + name + "正在操作" + account + "账户，金额为"
						+ ioCash + "，当前金额为" + account.getCash());
				account.setCash(account.getCash() + ioCash);
				System.out.println("写：" + name + "操作" + account + "账户成功，金额为"
						+ ioCash + "，当前金额为" + account.getCash());
				// 释放写锁
				lock.writeLock().unlock();
			}
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
