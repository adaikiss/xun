/**
 * 2011-3-18
 */
package org.adaikiss.kay.trys.concurrency;

/**
 * hlw
 *
 */
public class Class2 {
	public int times = 10;
	public int time = 200;
	public int current = 0;
	public synchronized void out(String str){
		System.out.println(str);
		this.current = (this.current + 1)%3;
		try {
			this.wait(this.time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Class2 caller = new Class2();
		IThread t1 = new IThread("A", caller, 0);
		IThread t2 = new IThread("B", caller, 1);
		IThread t3 = new IThread("C", caller, 2);
		t1.start();
		t2.start();
		t3.start();
	}

}

class IThread extends Thread{
	private String name;
	private Class2 caller;
	private int id;
	public IThread(String name, Class2 caller, int id){
		this.name = name;
		this.caller = caller;
		this.id = id;
	}

	@Override
	public void run() {
		try {
			for(int i = 0;i<caller.times;i++){
				while(caller.current != this.id){
					Thread.sleep(caller.time);
				}
				caller.out(name);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}