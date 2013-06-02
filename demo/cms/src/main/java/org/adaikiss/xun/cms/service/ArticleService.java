/**
 * 
 */
package org.adaikiss.xun.cms.service;

import org.adaikiss.xun.cms.entity.Article;

/**
 * @author hlw
 *
 */
public interface ArticleService {
	public Article add(String title, Long channelId);

	public Article update(Article article);
}
