/**
 * 
 */
package org.adaikiss.xun.mvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author hlw
 * 
 */
@Configuration
@EnableScheduling
@ComponentScan(basePackages = { "org.adaikiss.xun.repository" })
public class AppConfig {
	@Bean
	public ResourceLoader resourceLoader() {
		return new PathMatchingResourcePatternResolver();
	}
}
