/**
 * 
 */
package org.adaikiss.xun.core.enumeration;

/**
 * 用户状态
 * @author hlw
 * 
 */
public enum UserStatus {
	Restricted("限制"), Trashed("删除"), Normal("正常"), Frozen("冻结");
	private String description;

	public String getDescription() {
		return this.description;
	}

	UserStatus(String description) {
		this.description = description;
	}
}
