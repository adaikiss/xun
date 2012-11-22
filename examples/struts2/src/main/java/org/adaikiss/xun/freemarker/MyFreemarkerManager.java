package org.adaikiss.xun.freemarker;

import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.struts2.views.freemarker.FreemarkerManager;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

import freemarker.template.Configuration;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;

/**
 * @author hlw
 * 
 */
@Component("freemarkerManager")
public class MyFreemarkerManager extends FreemarkerManager {

	@Override
	protected Configuration createConfiguration(ServletContext servletContext) throws TemplateException {
		Configuration configuration = super.createConfiguration(servletContext);

		// 设置标签类型([]、<>),[]这种标记解析要快些
		configuration.setTagSyntax(Configuration.AUTO_DETECT_TAG_SYNTAX);

		// 取出上下文
		ApplicationContext applicationContext = WebApplicationContextUtils
				.getRequiredWebApplicationContext(servletContext);

		// 获取实现TemplateDirectiveModel的bean
		Map<String, TemplateDirectiveModel> beans = applicationContext.getBeansOfType(TemplateDirectiveModel.class);

		for (String key : beans.keySet()) {
			configuration.setSharedVariable(key, beans.get(key));
		}
		return configuration;
	}

}
