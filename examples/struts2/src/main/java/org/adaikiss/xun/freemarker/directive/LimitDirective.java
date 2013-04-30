package org.adaikiss.xun.freemarker.directive;

import java.io.IOException;
import java.util.Map;

import org.adaikiss.xun.freemarker.FreemarkerUtil;
//import org.adaikiss.xun.utils.StringUtil;
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
@Component("limit")
public class LimitDirective implements TemplateDirectiveModel {

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
			throws TemplateException, IOException {
		String value = FreemarkerUtil.parseString("value", params, "");
		int limit = FreemarkerUtil.parseInteger("limit", params, 12);
		String ellipsis = FreemarkerUtil.parseString("ellipsis", params, "...");
		if (!StringUtils.isBlank(value)) {
			//value = StringUtil.limit(value, limit, ellipsis, 2);
		}
		env.getOut().write(value);
	}
}
