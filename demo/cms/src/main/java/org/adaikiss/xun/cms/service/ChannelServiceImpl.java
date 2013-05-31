/**
 * 
 */
package org.adaikiss.xun.cms.service;

import java.util.List;

import org.adaikiss.xun.cms.entity.Channel;
import org.adaikiss.xun.cms.repository.ChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author hlw
 *
 */
public class ChannelServiceImpl implements ChannelService {

	@Autowired
	private ChannelRepository channelRepository;

}
