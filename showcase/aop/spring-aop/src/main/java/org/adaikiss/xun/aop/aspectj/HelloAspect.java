/**
 * 
 */
package org.adaikiss.xun.aop.aspectj;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author hlw
 *
 */
@Aspect
@Component
public class HelloAspect {

	@Pointcut("execution(* org.adaikiss.xun.aop..*(..))")
	public void hello(){
		
	}

	@Before("hello()")
	public void before(){
		System.out.println("AOP:call before...");
	}
}
