/**
 * 
 */
package org.adaikiss.xun;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * @author hlw
 * 
 */
@Component
@Aspect
public class XunAdvisor {

	@Pointcut("execution(* org.adaikiss.xun.action..*(..)) && @annotation(org.adaikiss.xun.OperationLog)")
	public void doAspect(){
		
	}

	@Before("doAspect()&& @annotation(operationLog)")
	public void doBefore(JoinPoint joinPoint, OperationLog operationLog){
		System.out.println("-------------------------" + operationLog.operation());
		Object target = joinPoint.getTarget();
		Class<?> targetType = joinPoint.getTarget().getClass();
		try {
			Field field = targetType.getDeclaredField(operationLog.arg0());
			field.setAccessible(true);
			Object value = field.get(target);
			System.out.println(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("开始执行方法 " + getMethod(joinPoint));
	}

	@Around("doAspect()")
	public void doAround(ProceedingJoinPoint proceedingJoinPoint){
		System.out.println("开始执行方法------> " + getMethod(proceedingJoinPoint));
		try {
			proceedingJoinPoint.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		System.out.println("<------执行方法结束 " + getMethod(proceedingJoinPoint));
	}

	@After("doAspect()")
	public void doAfter(JoinPoint joinPoint){
		System.out.println("执行方法 " + getMethod(joinPoint) + " 结束");
	}

	@AfterReturning(pointcut = "doAspect()", returning = "returnVal")
	public void doAfterReturn(JoinPoint joinPoint, Object returnVal){
		System.out.println("执行方法 " + getMethod(joinPoint) + " 返回 " + returnVal);
	}

	@AfterThrowing(pointcut = "doAspect()", throwing = "throwable")
	public void doAfterException(JoinPoint joinPoint, Throwable throwable){
		System.out.println("执行方法 " + getMethod(joinPoint) + " 抛出异常 " + throwable);
	}

	public Method getMethod(JoinPoint joinPoint){
		Signature signature = joinPoint.getSignature();
		if(signature instanceof MethodSignature){
			return ((MethodSignature)signature).getMethod();
		}
		return null;
	}
}