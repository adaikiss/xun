/**
 * 
 */
package org.adaikiss.xun.concurrency.thread;

import java.util.ArrayList;
import java.util.List;

/**
 * Thread can handle it's interruption or ignore it.<br/>
 * the <code>isInterrupted()</code> method doesn't change the value of interrupted attribute while the static method <code>interrupted()</code> sets it to false.
 * @author HuLingwei
 *
 */
public class InterruptionShowcase {

	private int n = 400;
	private int max = 5000;
	List<Thread> tasks;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		InterruptionShowcase s = new InterruptionShowcase();
		s.tasks = new ArrayList<Thread>();
		for(int i = 0;i<10;i++){
			s.tasks.add(s.new Counter(i+ ""));
		}
		for(Thread t : s.tasks){
			t.start();
		}
	}

	void interruptAll(Thread t){
		for(Thread th : tasks){
			if(!t.equals(th)){
				th.interrupt();
			}
		}
	}

	class Counter extends Thread{

		public Counter(String name) {
			super(name);
		}

		@Override
		public void run() {
			for(int i = 0;i<max;i++){
				if(this.isInterrupted()){
					System.err.println("Thread-" + getName() + " is interrupted!");
					return;
				}
				if(i == n){
					System.out.println("Thread-" + getName() + " find the number!");
					interruptAll(this);
					break;
				}
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					System.err.println("Thread-" + getName() + " is interrupted!");
					return;
				}
			}
			
		}
		
	}
}
