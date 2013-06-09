/**
 * 
 */
package org.adaikiss.xun.core.freemarker.directive;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.adaikiss.xun.cms.entity.Article;
import org.adaikiss.xun.cms.service.ArticleService;
import org.adaikiss.xun.core.util.FreemarkerUtil;
import org.adaikiss.xun.core.util.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springside.modules.orm.PropertyFilter;

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
@Component("article_page")
public class ArticlePageDirective  implements TemplateDirectiveModel{

	@Autowired
	private ArticleService articleService;

	@Override
	@SuppressWarnings({"rawtypes", "unchecked"})
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		FreemarkerUtil.convertToStringMap(params, PropertyFilter.FILTER_PREFIX);
		List<PropertyFilter> filters = PropertyFilter.buildFromMap(params);
		PageRequest pageRequest = PageHelper.buildFromMap(params);
		Page<Article> page = articleService.findPage(filters, pageRequest);
		if(null != body){
			if(loopVars.length > 0){
				loopVars[0] = ObjectWrapper.BEANS_WRAPPER.wrap(page);
			}
			body.render(env.getOut());
		}
	}

}
