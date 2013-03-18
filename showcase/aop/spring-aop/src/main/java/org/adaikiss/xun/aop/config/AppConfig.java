/**
 * 
 */
package org.adaikiss.xun.aop.config;

import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author hlw
 * 
 */
@Configuration
@EnableScheduling
@ComponentScan(basePackages = { "org.adaikiss.xun.aop" })
public class AppConfig {

	@Bean
	public AnnotationAwareAspectJAutoProxyCreator autoProxyCreator() {
		AnnotationAwareAspectJAutoProxyCreator autoProxyCreator = new AnnotationAwareAspectJAutoProxyCreator();
		autoProxyCreator.setProxyTargetClass(true);
		return autoProxyCreator;
	}

}
