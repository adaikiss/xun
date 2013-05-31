/**
 * 
 */
package org.adaikiss.xun.cms.service;

import java.util.Date;

import org.adaikiss.xun.cms.entity.Article;
import org.adaikiss.xun.cms.entity.Channel;
import org.adaikiss.xun.cms.repository.ArticleRepository;
import org.adaikiss.xun.core.entity.Member;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author hlw
 *
 */
@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticleRepository articleRepository;

	@Override
	public Article add(String title, Long channelId) {
		Article entity = new Article();
		entity.setTitle(title);
		entity.setDate(new Date());
		entity.setPublisher(((Member)SecurityUtils.getSubject().getPrincipal()));
		entity.setChannel(new Channel(channelId));
		articleRepository.save(entity);
		return entity;
	}

}
