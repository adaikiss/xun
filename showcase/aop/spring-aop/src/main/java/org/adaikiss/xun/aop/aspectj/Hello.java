/**
 * 
 */
package org.adaikiss.xun.aop.aspectj;

import org.adaikiss.xun.aop.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author hlw
 *
 */
@Component
public class Hello {

	public void say(){
		System.out.println("Hi!");
	}

	public void hello(){
		System.out.println("Hello world!");
		say();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AnnotationConfigApplicationContext rootContext = new AnnotationConfigApplicationContext();
		rootContext.register(AppConfig.class);
		rootContext.refresh();
		Hello hello = rootContext.getBean(Hello.class);
		hello.hello();
//		new Hello().hello();
	}

}
