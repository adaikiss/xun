/**
 * 
 */
package org.adaikiss.xun.cms.repository;

import java.util.List;

import org.adaikiss.xun.cms.entity.Channel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author hlw
 *
 */
public interface ChannelRepository extends JpaRepository<Channel, Long>, JpaSpecificationExecutor<Channel> {
	/**
	 * 查找所有顶级栏目
	 * @return
	 */
	public List<Channel> findByParentIsNull();
}
