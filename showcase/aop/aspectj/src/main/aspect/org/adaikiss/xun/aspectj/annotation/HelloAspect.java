/**
 * 
 */
package org.adaikiss.xun.aspectj.annotation;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author hlw
 *
 */
@Aspect
public class HelloAspect {
	@Pointcut("call(public void Hello.say*(..))")
	public void say(){
		
	}

	@Before("say()")
	public void before(){
		System.out.println("AOP:before say...");
	}

	@After("say()")
	public void after(){
		System.out.println("AOP:after say...");
	}
}
