/**
 * 
 */
package org.adaikiss.xun.cms.repository;

import org.adaikiss.xun.cms.entity.Article;
import org.adaikiss.xun.cms.entity.Channel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author hlw
 *
 */
public interface ArticleRepository extends JpaRepository<Article, Long> {
	Page<Article> findByChannelOrderByWeightDesc(Channel channel, Pageable pageRequest);
}
