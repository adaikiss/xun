/**
 * 
 */
package org.adaikiss.xun.aspectj.aj;

/**
 * @author hlw
 *
 */
public aspect HelloAspect {

	pointcut say() : call(public void Hello.say*(..));

	before() : say() {
		System.out.println("AOP:before say...");
	}
	after() : say() {
		System.out.println("AOP:after say...");
	}
}
