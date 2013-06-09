/**
 * 
 */
package org.adaikiss.xun.cms.service;

import java.util.List;

import org.adaikiss.xun.cms.entity.Channel;

/**
 * @author hlw
 *
 */
public interface ChannelService {
	List<Channel> findByParentId(Long parentId);
	List<Channel> findByParentIdEager(Long parentId);
}
