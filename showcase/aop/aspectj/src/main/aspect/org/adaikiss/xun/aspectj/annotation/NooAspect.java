/**
 * 
 */
package org.adaikiss.xun.aspectj.annotation;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author hlw
 *
 */
@Aspect
public class NooAspect {

	@Pointcut("call(* org.adaikiss.xun.aspectj..Noo.hello(..))")
	public void hello(){
		
	}

	@Before("hello()")
	public void callHello(){
		System.out.println("AOP:call method Noo.hello");
	}
}
