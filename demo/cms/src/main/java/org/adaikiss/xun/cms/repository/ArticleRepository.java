/**
 * 
 */
package org.adaikiss.xun.cms.repository;

import java.util.List;

import org.adaikiss.xun.cms.entity.Article;
import org.adaikiss.xun.cms.entity.Channel;
import org.adaikiss.xun.cms.enumeration.PostStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author hlw
 *
 */
public interface ArticleRepository extends JpaRepository<Article, Long> , JpaSpecificationExecutor<Article>{
	Page<Article> findByChannelOrderByWeightDesc(Channel channel, Pageable pageRequest);

	List<Article> findByChannelOrderByIdDesc(Channel channel, Pageable pageRequest);

	Article findByIdAndStatus(Long id, PostStatus status);
}
