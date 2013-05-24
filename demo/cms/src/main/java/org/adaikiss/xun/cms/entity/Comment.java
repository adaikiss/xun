/**
 * 
 */
package org.adaikiss.xun.cms.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.adaikiss.xun.core.entity.IdEntity;
import org.adaikiss.xun.core.entity.Member;
import org.adaikiss.xun.core.util.Constant;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.google.common.collect.Lists;

/**
 * 评论
 * 
 * @author hlw
 * 
 */
@Entity
@Table(name = Constant.TABLE_PREFIX + "comment")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonAutoDetect(fieldVisibility = Visibility.NONE, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE)
@JsonInclude(Include.NON_EMPTY)
public class Comment extends IdEntity {
	/**
	 * 发布者
	 */
	private Member publisher;
	/**
	 * 关联父评论
	 */
	private Comment parent;

	/**
	 * 关联子评论
	 */
	private List<Comment> children = Lists.newArrayList();

	/**
	 * 关联文章
	 */
	private Article article;
	/**
	 * 评论内容
	 */
	private String content;
	/**
	 * 评论时间
	 */
	private Date date;

	/**
	 * 评论时所在IP
	 */
	private String ip;

	@ManyToOne
	@JoinColumn(name = "user_id")
	public Member getPublisher() {
		return publisher;
	}

	public void setPublisher(Member publisher) {
		this.publisher = publisher;
	}

	@ManyToOne
	@JoinColumn(name = "parent_id")
	public Comment getParent() {
		return parent;
	}

	public void setParent(Comment parent) {
		this.parent = parent;
	}

	@OneToMany
	@JoinColumn(name = "parent_id")
	public List<Comment> getChildren() {
		return children;
	}

	public void setChildren(List<Comment> children) {
		this.children = children;
	}

	@ManyToOne
	@JoinColumn(name = "article_id")
	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
}
