/**
 * 
 */
package org.adaikiss.xun.cms.service;

import java.util.List;

import org.adaikiss.xun.cms.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springside.modules.orm.PropertyFilter;

/**
 * @author hlw
 *
 */
public interface ArticleService {
	public Article add(String title, Long channelId);

	public Article update(Article article);

	public void delete(Article article);

	public Page<Article> findPage(List<PropertyFilter> filters, PageRequest pageRequest);
}
