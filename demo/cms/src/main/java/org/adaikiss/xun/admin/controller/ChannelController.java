/**
 * 
 */
package org.adaikiss.xun.admin.controller;

import java.util.List;

import org.adaikiss.xun.cms.entity.Channel;
import org.adaikiss.xun.cms.repository.ChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author hlw
 *
 */
@Controller
@RequestMapping("/admin/channel")
public class ChannelController {
	@Autowired
	private ChannelRepository channelRepository;

	@RequestMapping(value = "/tree")
	public @ResponseBody List<Channel> tree(){
		return channelRepository.findByParentIsNull();
	}

	
}
