/**
 * 上午10:04:56
 */
package org.adaikiss.kay.trys.socket;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * hlw
 * 
 */
public class CyclicBarrierTry {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CyclicBarrierTry tr = new CyclicBarrierTry();
		//创建障碍器，并设置MainTask为所有定数量的线程都达到障碍点时候所要执行的任务(Runnable)
        CyclicBarrier cb = new CyclicBarrier(7, tr.new MainTask());
        tr.new SubTask("A", cb).start();
        tr.new SubTask("B", cb).start();
        tr.new SubTask("C", cb).start();
        tr.new SubTask("D", cb).start();
        tr.new SubTask("E", cb).start();
        tr.new SubTask("F", cb).start();
        tr.new SubTask("G", cb).start(); 
	}

	/**
	 * 主任务
	 */
	class MainTask implements Runnable {
		public void run() {
			System.out.println(">>>>主任务执行了！<<<<");
		}
	}

	/**
	 * 子任务
	 */
	class SubTask extends Thread {
		private String name;
		private CyclicBarrier cb;

		SubTask(String name, CyclicBarrier cb) {
			this.name = name;
			this.cb = cb;
		}

		public void run() {
			System.out.println("[子任务" + name + "]开始执行了！");
			for (int i = 0; i < 999999; i++)
				; // 模拟耗时的任务
			System.out.println("[子任务" + name + "]开始执行完成了，并通知障碍器已经完成！");
			try {
				// 通知障碍器已经完成
				cb.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				e.printStackTrace();
			}
		}
	}
}
