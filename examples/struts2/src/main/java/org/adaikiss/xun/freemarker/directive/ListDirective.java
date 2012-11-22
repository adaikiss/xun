/**
 * 
 */
package org.adaikiss.xun.freemarker.directive;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.adaikiss.xun.freemarker.FreemarkerUtil;
import org.adaikiss.xun.utils.ReflectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import freemarker.core.Environment;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * @author hlw
 *
 */
@Component("list")
public class ListDirective implements TemplateDirectiveModel{

	@Autowired
	ApplicationContext applicationContext;

	@Override
	@SuppressWarnings({"rawtypes", "unchecked"})
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
			throws TemplateException, IOException {
		String typeName = FreemarkerUtil.parseString("type", params);
		//List<PropertyFilter> propertyFilters = PropertyFilter.
		Object bean = applicationContext.getBean(ReflectUtils.getClass("org.adaikiss.xun.service", typeName + "Service"));
		List<Object> result = (List<Object>)ReflectionUtils.invokeMethod(ReflectionUtils.findMethod(bean.getClass(), "findAll"), bean);
		if (null != body) {
			if (loopVars.length > 0) {
				loopVars[0] = ObjectWrapper.BEANS_WRAPPER.wrap(result);
			}
			body.render(env.getOut());
		}
	}

}
