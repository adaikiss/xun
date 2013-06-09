/**
 * 
 */
package org.adaikiss.xun.cms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.adaikiss.xun.cms.entity.Channel;
import org.adaikiss.xun.cms.enumeration.PostStatus;
import org.adaikiss.xun.cms.repository.ChannelRepository;
import org.adaikiss.xun.core.util.FrontUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author hlw
 *
 */
@Controller("channelController")
public class ChannelController {

	@Autowired
	private ChannelRepository channelRepository;

	@RequestMapping(value = {"/{alias}/"}, method = RequestMethod.GET)
	public String index(@PathVariable String alias, HttpServletRequest request, HttpServletResponse response,
			ModelMap model){
		Channel channel = channelRepository.findByAliasAndStatus(alias, PostStatus.Normal);
		if(null == channel){
			return FrontUtil.pageNotFound(request, response, model);
		}
		model.addAttribute("channel", channel);
		String tpl = channel.getRealChannelTpl();
		if(StringUtils.isBlank(tpl)){
			return FrontUtil.getDefaultChannelTpl();
		}
		return tpl;
	}
}
