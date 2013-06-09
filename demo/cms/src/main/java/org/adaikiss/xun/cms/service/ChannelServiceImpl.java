/**
 * 
 */
package org.adaikiss.xun.cms.service;

import java.util.List;

import org.adaikiss.xun.cms.entity.Channel;
import org.adaikiss.xun.cms.repository.ChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author hlw
 *
 */
@Service
@Transactional
public class ChannelServiceImpl implements ChannelService {

	@Autowired
	private ChannelRepository channelRepository;

	@Transactional(readOnly = true)
	@Override
	public List<Channel> findByParentId(Long parentId){
		if(null == parentId){
			return channelRepository.findByParentIsNull();
		}
		return channelRepository.findByParent_Id(parentId);
	}

	@Override
	public List<Channel> findByParentIdEager(Long parentId) {
		List<Channel> list = findByParentId(parentId);
		for(Channel channel : list){
			channel.hasChildren();
		}
		return list;
	}

}
