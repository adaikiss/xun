/**
 * 
 */
package org.adaikiss.xun.core.freemarker.directive;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.adaikiss.xun.cms.entity.Channel;
import org.adaikiss.xun.cms.service.ChannelService;
import org.adaikiss.xun.core.util.FreemarkerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
@Component("channel_list")
public class ChannelListDirective implements TemplateDirectiveModel{

	@Autowired
	private ChannelService channelService;

	@Override
	@SuppressWarnings({"rawtypes", "unchecked"})
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		Long parentId = FreemarkerUtil.parseLong("parentId",
				params);
		Boolean eager = FreemarkerUtil.parseBoolean("eager", params, Boolean.FALSE);
		List<Channel> list = eager?channelService.findByParentIdEager(parentId):channelService.findByParentId(parentId);
		if(null != body){
			if(loopVars.length > 0){
				loopVars[0] = ObjectWrapper.BEANS_WRAPPER.wrap(list);
			}
			body.render(env.getOut());
		}
	}

}
