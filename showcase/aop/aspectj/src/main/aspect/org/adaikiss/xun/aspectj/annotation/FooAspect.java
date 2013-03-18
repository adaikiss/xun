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
public class FooAspect {
	@Pointcut()
	public void hello(){
	}

	/**
	 * methods in package and of class Foo
	 */
	@Before("execution(* org.adaikiss.xun.aspectj..*(..)) && this(Foo)")
	public void callFromFoo(){
		System.out.println("AOP:Called from Foo");
	}

	/**
	 * methods in package and are instance of Foo
	 */
	@Before("execution(* org.adaikiss.xun.aspectj..*(..)) && this(foo)")
	public void callFromfoo(Foo foo){
		System.out.println("AOP:Called from instance of Foo");
	}

	/**
	 * non-static public methods in package and are of type Foo or subclass of Foo
	 */
	@Before("execution(public !static void Foo+.*(..))")
	public void fooOrSubclass(){
		System.out.println("AOP:non-static public methods in Foo or it's subclass");
	}
}
