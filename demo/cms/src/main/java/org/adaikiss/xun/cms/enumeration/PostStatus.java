/**
 * 
 */
package org.adaikiss.xun.cms.enumeration;

/**
 * 文章栏目状态
 * 
 * @author hlw
 * 
 */
public enum PostStatus {
	Normal("正常"), Closed("关闭");
	private String description;

	PostStatus(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}
}
