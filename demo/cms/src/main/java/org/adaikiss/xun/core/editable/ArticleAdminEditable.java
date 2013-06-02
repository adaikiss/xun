/**
 * 
 */
package org.adaikiss.xun.core.editable;

import org.adaikiss.xun.cms.enumeration.PostStatus;

/**
 * 后台可修改的Article属性
 * @author HuLingwei
 *
 */
public class ArticleAdminEditable {
	/**
	 * 标题
	 */
	String title;

	/**
	 * 内容
	 */
	String content;

	/**
	 * 摘要
	 */
	String excerpt;

	/**
	 * 文章作者
	 */
	String author;

	/**
	 * 文章来源
	 */
	String origin;

	/**
	 * 文章模板
	 */
	String tpl;

	/**
	 * 访问量
	 */
	long visits;

	/**
	 * 文章权重，权重高的排前面
	 */
	int weight;

	/**
	 * 关键字
	 */
	String keywords;

	/**
	 * 文章状态
	 */
	PostStatus status;
}
