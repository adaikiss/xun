/**
 * 
 */
package org.adaikiss.xun.mvc.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author hlw
 *
 */
@Configuration
@EnableScheduling
@ComponentScan(basePackages = { "org.adaikiss.xun.repository"})
public class AppConfig {

}
