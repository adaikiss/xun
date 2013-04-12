/**
 * 
 */
package org.adaikiss.xun.mvc.config;

import org.adaikiss.xun.freemarker.RichFreeMarkerViewResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

/**
 * @author hlw
 *
 */
@Configuration
public class FreeMarkerViewConfig {

	@Bean
	public FreeMarkerViewResolver freemarkerViewResolver(){
		FreeMarkerViewResolver resolver = new RichFreeMarkerViewResolver();
		resolver.setContentType("text/html; charset=UTF-8");
		resolver.setExposeSpringMacroHelpers(true);
		resolver.setRequestContextAttribute("rc");
		resolver.setPrefix("/");
		resolver.setSuffix(".ftl");
		return resolver;
	}

	@Bean
	public FreeMarkerConfigurer freemarkerConfig(){
		FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
		configurer.setTemplateLoaderPath("classpath:tpl/");
		configurer.setConfigLocation(new ClassPathResource("freemarker.properties"));
		return configurer;
	}

}
