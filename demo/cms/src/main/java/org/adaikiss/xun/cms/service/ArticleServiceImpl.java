/**
 * 
 */
package org.adaikiss.xun.cms.service;

import java.util.Date;
import java.util.List;

import org.adaikiss.xun.cms.entity.Article;
import org.adaikiss.xun.cms.entity.Channel;
import org.adaikiss.xun.cms.repository.ArticleRepository;
import org.adaikiss.xun.cms.repository.ChannelRepository;
import org.adaikiss.xun.core.editable.ArticleAdminEditable;
import org.adaikiss.xun.core.entity.Member;
import org.adaikiss.xun.core.security.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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

	@Autowired
	private ChannelRepository channelRepository;

	@Override
	public Article add(String title, Long channelId) {
		Channel channel = channelRepository.findOne(channelId);
		if(null == channel){
			throw new RuntimeException("栏目不存在!");
		}
		Article entity = new Article();
		entity.setTitle(title);
		entity.setDate(new Date());
		entity.setPublisher(((Member)SecurityUtils.getSubject().getPrincipal()));
		entity.setChannel(channel);
		List<Article> prevs = articleRepository.findByChannelOrderByIdDesc(channel, new PageRequest(0, 1));
		Article prev = prevs.get(0);
		if(null != prev){
			entity.setPrev(prev);
			prev.setNext(entity);
		}
		articleRepository.save(entity);
		return entity;
	}

	@Override
	public Article update(Article article) {
		Article old = articleRepository.findOne(article.getId());
		BeanUtils.copyProperties(article, old, ArticleAdminEditable.class);
		old.setModified(new Date());
		old.setModifier(SecurityUtil.getMember());
		articleRepository.save(old);
		return old;
	}

}
