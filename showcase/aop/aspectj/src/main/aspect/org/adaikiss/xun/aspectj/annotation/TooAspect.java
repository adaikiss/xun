/**
 * 
 */
package org.adaikiss.xun.aspectj.annotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author hlw
 *
 */
@Aspect
public class TooAspect {

	@Pointcut("within(org.adaikiss.xun.aspectj..Too)")
	public void hello(){
		
	}

	@Before("hello()")
	public void withInToo(JoinPoint jp){
		System.out.println("AOP:code within Too");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
