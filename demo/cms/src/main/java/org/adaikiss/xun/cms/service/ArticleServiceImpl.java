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
import org.adaikiss.xun.core.entity.Member;
import org.adaikiss.xun.core.jpa.specification.BaseSpecifications;
import org.adaikiss.xun.core.security.SecurityUtil;
import org.adaikiss.xun.core.util.BeanUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.PropertyFilter;

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
		BeanUtil.copyProperties(article, old, new String[]{"title", "content", "excerpt", "author", "origin", "tpl", "visits", "weight", "keywords", "status"});
		old.setModified(new Date());
		old.setModifier(SecurityUtil.getMember());
		articleRepository.save(old);
		return old;
	}

	@Override
	public void delete(Article article) {
		Article prev = article.getPrev();
		Article next = article.getNext();
		if(prev != null){
			prev.setNext(next);
		}
		if(next != null){
			next.setPrev(prev);
		}
		articleRepository.delete(article);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Article> findPage(List<PropertyFilter> filters, PageRequest pageRequest){
		Specification<Article> s = BaseSpecifications.propertyFilter(filters);
		return articleRepository.findAll(s, pageRequest);
	}
}
