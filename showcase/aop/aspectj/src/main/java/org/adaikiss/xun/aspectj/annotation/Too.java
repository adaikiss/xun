/**
 * 
 */
package org.adaikiss.xun.aspectj.annotation;

/**
 * @author hlw
 *
 */
public class Too {

	public Too(){
		super();
	}

	public void hello(){
		System.out.println("hello everyone!");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Too().hello();
	}

}
