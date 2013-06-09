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
	/**
	 * 正常
	 */
	Normal("正常"),
	/**
	 * 关闭
	 */
	Closed("关闭"),
	/**
	 * 待审核
	 */
	UnderVerify("待审核");
	private String description;

	PostStatus(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}
}
