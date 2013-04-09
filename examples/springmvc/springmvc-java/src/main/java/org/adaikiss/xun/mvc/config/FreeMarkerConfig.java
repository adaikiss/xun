/**
 * 
 */
package org.adaikiss.xun.mvc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

/**
 * @author hlw
 *
 */
public class FreeMarkerConfig {
	@Autowired
	private ResourceLoader resourceLoader;

	@Bean
	public FreeMarkerViewResolver freemarkerViewResolver(){
		FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
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
		configurer.setTemplateLoaderPath("/WEB-INF/tpl/");
		configurer.setConfigLocation(new ClassPathResource("freemarker.properties"));
		return configurer;
	}

}
