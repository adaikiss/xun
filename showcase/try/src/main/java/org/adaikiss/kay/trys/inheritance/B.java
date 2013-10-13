/**
 * 下午03:41:25
 */
package org.adaikiss.kay.trys.inheritance;

/**
 * hlw
 * 
 */
public class B extends A {
	private final int num;

	public B(int num) {
		super(num - 1);
		this.num = num;
	}

	@Override
	public void show() {
		System.out.println(getNum() + ", " + super.getNum());
	}

	public int getNum() {
		return num;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new B(4).show();
	}

}
