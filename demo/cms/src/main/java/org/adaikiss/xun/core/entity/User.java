/**
 * 
 */
package org.adaikiss.xun.core.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import org.adaikiss.xun.core.enumeration.UserStatus;

/**
 * 抽象用户
 * 
 * @author hlw
 * 
 */
@MappedSuperclass
public abstract class User extends IdEntity{
	/**
	 * 用户名
	 */
	protected String loginName;
	/**
	 * 密码
	 */
	protected String password;
	/**
	 * 真实姓名
	 */
	protected String realName;
	/**
	 * 昵称
	 */
	protected String displayName;
	/**
	 * 用户状态
	 */
	@Enumerated(EnumType.STRING)
	protected UserStatus status;
	/**
	 * 创建时间
	 */
	protected Date createTime;
	/**
	 * 最近登录时间
	 */
	protected Date lastLoginTime;

	/**
	 * 用户数据
	 */
	protected UserMeta meta;

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "user", cascade = {CascadeType.REMOVE})
	@PrimaryKeyJoinColumn
	public UserMeta getMeta() {
		return meta;
	}

	public void setMeta(UserMeta meta) {
		this.meta = meta;
	}
}
