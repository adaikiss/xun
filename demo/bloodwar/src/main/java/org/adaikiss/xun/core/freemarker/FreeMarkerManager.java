/**
 * 
 */
package org.adaikiss.xun.core.freemarker;

import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.ext.beans.BeansWrapper;
import freemarker.template.Configuration;
import freemarker.template.TemplateDirectiveModel;

/**
 * @author hlw
 *
 */
public class FreeMarkerManager implements ApplicationListener<ContextRefreshedEvent>, ApplicationContextAware{
	
	private ApplicationContext ctx;

	@Autowired
	private FreeMarkerConfigurer freemarkerConfigurer;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		ctx = applicationContext;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		config();
	}

	private void config() {
		Configuration configuration = freemarkerConfigurer.getConfiguration();

		// 设置标签类型([]、<>),[]这种标记解析要快些
		configuration.setTagSyntax(Configuration.AUTO_DETECT_TAG_SYNTAX);

		// 获取实现TemplateDirectiveModel的bean
		Map<String, TemplateDirectiveModel> beans = ctx.getBeansOfType(TemplateDirectiveModel.class);

		for (String key : beans.keySet()) {
			configuration.setSharedVariable(key, beans.get(key));
		}

		//添加静态方法
		configuration.setSharedVariable("statics", BeansWrapper.getDefaultInstance().getStaticModels());
	}
}
