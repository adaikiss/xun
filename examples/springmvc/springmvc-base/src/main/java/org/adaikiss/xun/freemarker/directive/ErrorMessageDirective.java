/**
 * 
 */
package org.adaikiss.xun.freemarker.directive;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
/**
 * @author hlw
 *
 */
@Component("error")
public class ErrorMessageDirective implements TemplateDirectiveModel {

	@Override
	@SuppressWarnings({ "rawtypes" })
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
			throws TemplateException, IOException {
		String key = FreemarkerUtil.parseString("key", params);
		if(StringUtils.isBlank(key)){
			throw new RuntimeException("key can not be blank!");
		}
		String errorsName = FreemarkerUtil.parseString("errorsName", params, "errors");
		String wrapper = FreemarkerUtil.parseString("wrapper", params, "<span class=\"error\"></span>");
		TemplateModel model = env.getVariable(errorsName);
		Map<String, Object> errors = (Map<String, Object>)model;
		errors.get(key);
	}
}
