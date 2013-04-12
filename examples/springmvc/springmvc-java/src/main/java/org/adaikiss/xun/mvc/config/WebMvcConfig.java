package org.adaikiss.xun.mvc.config;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2CollectionHttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.accept.ParameterContentNegotiationStrategy;
import org.springframework.web.accept.PathExtensionContentNegotiationStrategy;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

@Configuration
@Import({ AppConfig.class, FreeMarkerViewConfig.class })
@EnableScheduling
@ComponentScan(basePackages = { "org.adaikiss.xun.controller",
		"org.adaikiss.xun.directive" }, includeFilters = {
		@ComponentScan.Filter(value = org.springframework.stereotype.Controller.class, type = FilterType.ANNOTATION),
		@ComponentScan.Filter(value = org.springframework.stereotype.Component.class, type = FilterType.ANNOTATION) })
public class WebMvcConfig extends WebMvcConfigurationSupport {

	@Autowired
	private FreeMarkerViewResolver freemarkerViewResolver;

	@Bean
	public ContentNegotiationManager mvcContentNegotiationManager() {
		// ContentNegotiationStrategy[] strategies = new
		// ContentNegotiationStrategy[] {
		// new ParameterContentNegotiationStrategy(getDefaultMediaTypes()),
		// new PathExtensionContentNegotiationStrategy(
		// getDefaultMediaTypes()) };
		ContentNegotiationManager contentNegotiationManager = super
				.mvcContentNegotiationManager();
		contentNegotiationManager
				.addFileExtensionResolvers(
						new ParameterContentNegotiationStrategy(
								getDefaultMediaTypes()),
						new PathExtensionContentNegotiationStrategy(
								getDefaultMediaTypes()));
		return contentNegotiationManager;
	}

	protected Map<String, MediaType> getDefaultMediaTypes() {
		Map<String, MediaType> mediaTypes = super.getDefaultMediaTypes();
		mediaTypes.put("html", MediaType.TEXT_HTML);
		return mediaTypes;
	}

	@Override
	public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
		configurer.setDefaultTimeout(30 * 1000L);
	}

	@Override
	protected void configureMessageConverters(
			List<HttpMessageConverter<?>> converters) {
		addDefaultHttpMessageConverters(converters);
		@SuppressWarnings("rawtypes")
		Jaxb2CollectionHttpMessageConverter jaxb2CollectionHttpMessageConverter = new Jaxb2CollectionHttpMessageConverter();
		converters.add(jaxb2CollectionHttpMessageConverter);
	}

	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("chat");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations(
				"resources/");
	}

	@Override
	public void configureDefaultServletHandling(
			DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Bean
	public ViewResolver viewResolver() {
		ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
		resolver.setContentNegotiationManager(mvcContentNegotiationManager());
		resolver.setViewResolvers(Arrays
				.asList(new ViewResolver[] { freemarkerViewResolver }));
		return resolver;
	}
}
