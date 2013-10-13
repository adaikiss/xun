package org.adaikiss.kay.trys.concurrency;

public class Class1 {

	public static void main(String[] args) {
		MyClass1 myClass = new MyClass1(); 
		new MyThread(myClass).start();
		new MyThread1(myClass).start();
	}

}

class MyClass1 {

	Object obj = new Object();
	Object obj1 = new Object();

	public synchronized void m1() {
		System.out.println("m1-1");
		try {
			this.wait();   //暂停改当前线程，并且会释放锁
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("m1-2");
		try {
			Thread.sleep(1000);   //m1和m2同时暂停1秒钟 
			System.out.println("m1-3");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("m1-4");
		m2();
	}

	public synchronized void m2() {
		System.out.println("m2-1");
		this.notifyAll();  //叫醒m1
		System.out.println("m2-2");
		try {
			Thread.sleep(1000);
			System.out.println("m2-3");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("m2-4");
		m1();
	}
}

class MyThread extends Thread {
	private MyClass1 myClass;

	public MyThread(MyClass1 myClass) {
		this.myClass = myClass;
	}

	@Override
	public void run() {
		myClass.m1();
	}

}

class MyThread1 extends Thread {
	private MyClass1 myClass;

	public MyThread1(MyClass1 myClass) {
		this.myClass = myClass;
	}

	@Override
	public void run() {
                try {  //暂停10毫秒是为了让m1先执行wait
			Thread.sleep(10);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		myClass.m2();
	}
}