/**
 * 下午03:16:07
 */
package org.adaikiss.kay.trys.concurrency;

/**
 * hlw
 * 
 */
public class ProductorCustomer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ProductorCustomer pc = new ProductorCustomer();
		Godown g = pc.new Godown();
		Productor p1 = pc.new Productor(10, 10, g, "PA");
		Productor p2 = pc.new Productor(12, 13, g, "PB");
		Productor p3 = pc.new Productor(14, 9, g, "PC");
		Productor p4 = pc.new Productor(16, 7, g, "PD");
		Customer c1 = pc.new Customer(8, 20, g, "CE");
		Customer c2 = pc.new Customer(20, 8, g, "CF");
		Customer c3 = pc.new Customer(14, 10, g, "CG");
		Customer c4 = pc.new Customer(16, 18, g, "CH");
		p1.start();
		p2.start();
		p3.start();
		p4.start();
		c1.start();
		c2.start();
		c3.start();
		c4.start();
	}

	class Godown {
		public static final int max_num = 100;
		public int cur_num;

		public synchronized void increase(int num, String productor) {
			if (cur_num + num > max_num) {
				cur_num = max_num;
				System.out.println(productor + "今天生产了" + num
						+ "食物, 太多了, 仓库放不下, 浪费掉了!");
				return;
			}
			cur_num += num;
			System.out.println(productor + "今天生产了" + num + "食物, 现在总量为"
					+ cur_num);
		}

		public synchronized void decrease(int num, String customer) {
			if (cur_num == 0) {
				System.out.println("仓库里已经没有食物了, " + customer + "饿了一顿!");
				return;
			}
			if(cur_num <num){
				cur_num = 0;
				System.out.println("仓库里食物不足, " + customer + "没有吃饱!");
				return;
			}
			cur_num -= num;
			System.out
					.println(customer + "已经使用了" + num + "食物, 现在总量为" + cur_num);
		}
	}

	class Productor extends Thread {
		private int rate;
		private Godown godown;
		private int life;
		private String name;

		public Productor(int rate, int life, Godown godown, String name) {
			this.rate = rate;
			this.life = life;
			this.godown = godown;
			this.name = name;
		}

		@Override
		public void run() {
			while (life-- > 0) {
				System.out.println(name + "花一天的时间生产...");
				try {
					sleep(5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				this.godown.increase(rate, name);
			}
		}
	}

	class Customer extends Thread {
		private int rate;
		private Godown godown;
		private int life;
		private String name;

		public Customer(int rate, int life, Godown godown, String name) {
			this.rate = rate;
			this.godown = godown;
			this.life = life;
			this.name = name;
		}

		@Override
		public void run() {
			while (life-- > 0) {
				System.out.println(name + "运动一整天...");
				try {
					sleep(5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				this.godown.decrease(rate, name);
			}
		}
	}
}
