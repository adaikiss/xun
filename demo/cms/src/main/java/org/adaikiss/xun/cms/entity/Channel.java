/**
 * 
 */
package org.adaikiss.xun.cms.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.adaikiss.xun.cms.enumeration.PostStatus;
import org.adaikiss.xun.core.entity.Employee;
import org.adaikiss.xun.core.entity.IdEntity;
import org.adaikiss.xun.core.util.Constant;
import org.adaikiss.xun.core.util.PreferenceHelper;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;

/**
 * 栏目
 * 
 * @author hlw
 * 
 */
@Entity
@Table(name = Constant.TABLE_PREFIX + "channel")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonAutoDetect(fieldVisibility = Visibility.NONE, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE)
@JsonInclude(Include.NON_EMPTY)
public class Channel extends IdEntity {
	/**
	 * 栏目名称
	 */
	@JsonProperty
	private String name;
	/**
	 * 栏目别名，用于url显示
	 */
	@JsonProperty
	private String alias;

	/**
	 * 显示顺序
	 */
	@JsonProperty
	private int order;

	/**
	 * 父栏目
	 */
	private Channel parent;
	/**
	 * 子栏目
	 */
	@JsonProperty
	private List<Channel> children = Lists.newArrayList();

	/**
	 * 栏目下的文章
	 */
	private List<Article> articles = Lists.newArrayList();

	/**
	 * 图标
	 */
	private String icon;
	/**
	 * 描述
	 */
	private String description;

	/**
	 * 栏目模板
	 */
	private String channelTpl;
	/**
	 * 页面模板
	 */
	private String articleTpl;

	/**
	 * 栏目状态
	 */
	private PostStatus status;

	/**
	 * 创建者
	 */
	private Employee creator;

	public Channel(){
		
	}

	public Channel(Long id){
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	@Column(name = "order_no")
	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	@ManyToOne
	@JoinColumn(name = "parent_id")
	public Channel getParent() {
		return parent;
	}

	public void setParent(Channel parent) {
		this.parent = parent;
	}

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "parent")
	@OrderBy("order")
	public List<Channel> getChildren() {
		return children;
	}

	public void setChildren(List<Channel> children) {
		this.children = children;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "channel")
	@OrderBy("weight DESC, modified DESC")
	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getChannelTpl() {
		return channelTpl;
	}

	public void setChannelTpl(String channelTpl) {
		this.channelTpl = channelTpl;
	}

	public String getArticleTpl() {
		return articleTpl;
	}

	public void setArticleTpl(String articleTpl) {
		this.articleTpl = articleTpl;
	}

	@Enumerated(EnumType.STRING)
	public PostStatus getStatus() {
		return status;
	}

	public void setStatus(PostStatus status) {
		this.status = status;
	}

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "creator_id")
	public Employee getCreator() {
		return creator;
	}

	public void setCreator(Employee creator) {
		this.creator = creator;
	}

	/**
	 * 根据ID获取子栏目
	 * 
	 * @param childId
	 * @return
	 */
	@Transient
	public Channel getChild(Long childId) {
		for (Channel channel : this.children) {
			if (channel.getId() == childId) {
				return channel;
			}
		}
		return null;
	}

	/**
	 * 添加子栏目
	 * 
	 * @param channels
	 */
	@Transient
	public void addChannel(Channel... channels) {
		for (Channel channel : channels) {
			channel.setOrder(this.children.size() + 1);
			this.children.add(channel);
		}
	}

	/**
	 * 添加栏目文章
	 * 
	 * @param articles
	 */
	@Transient
	public void addArticle(Article... articles) {
		for (Article article : articles) {
			this.articles.add(article);
		}
	}

	/**
	 * 根据ID获取栏目下的文章
	 * 
	 * @param articleId
	 * @return
	 */
	@Transient
	public Article getArticle(Long articleId) {
		for (Article article : this.articles) {
			if (article.getId() == articleId) {
				return article;
			}
		}
		return null;
	}

	/**
	 * 判断栏目下是否有文章
	 * 
	 * @return
	 */
	@Transient
	@JsonProperty("hasArticles")
	public boolean hasArticles() {
		return this.articles.size() != 0;
	}

	/**
	 * 判断是否有父栏目
	 * 
	 * @return
	 */
	@Transient
	public boolean hasParent() {
		return this.getParent() != null;
	}

	/**
	 * 判断是否有子栏目
	 * 
	 * @return
	 */
	@Transient
	public boolean hasChildren() {
		return this.children.size() != 0;
	}

	/**
	 * 获取真实的栏目模板， 优先取本栏目模板，如果没有则依次取上级栏目模板
	 * 
	 * @return
	 */
	@Transient
	public String getRealChannelTpl() {
		if (StringUtils.isNotBlank(this.channelTpl)) {
			return this.channelTpl;
		}
		return this.getTop().getRealChannelTpl();
	}

	/**
	 * 获取栏目下文章的真实模板，优先使用本栏目模板，如果没有则依次取上级栏目的文章模板
	 * 
	 * @return
	 */
	@Transient
	public String getRealArticleTpl() {
		if (StringUtils.isNotBlank(this.articleTpl)) {
			return this.articleTpl;
		}
		return this.getParent().getRealArticleTpl();
	}

	/**
	 * 获取当前栏目的顶级栏目
	 * @return
	 */
	@Transient
	public Channel getTop() {
		return this.hasParent() ? this.getParent().getTop() : this;
	}

	/**
	 * 获取栏目的访问url
	 * @return
	 */
	@Transient
	public String getUrl() {
		return this.alias + PreferenceHelper.getPreference().getPageSuffix();
	}
}
