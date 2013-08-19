/**
 * 
 */
package org.adaikiss.xun.aspectj.annotation;


/**
 * @author hlw
 *
 */
public class Hello {
	private int time = 0;
	public void say(){
		System.out.println("Hello, world!");
		if(time++ == 0){
			say();
		}
	}

	public static void main(String[] args){
		new Hello().say();
	}
}
