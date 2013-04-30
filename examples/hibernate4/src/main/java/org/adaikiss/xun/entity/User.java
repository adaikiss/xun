package org.adaikiss.xun.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.adaikiss.xun.enumeration.UserStatus;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.ForeignKey;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User extends IdEntity {
	private String niceName;
	private String displayName;
	private String loginName;
	private String password;
	private Date registered;
	private String email;
	private String url;
	private UserStatus status;
	private Credit credit;

	public User() {

	}

	public User(String niceName, String loginName) {
		this.niceName = niceName;
		this.loginName = loginName;
	}

	public User(String niceName, String displayName, String loginName){
		this(niceName, loginName);
		this.displayName = displayName;
	}

	@Column(name = "nice_name")
	public String getNiceName() {
		return niceName;
	}

	public void setNiceName(String niceName) {
		this.niceName = niceName;
	}

	@Column(name = "display_name")
	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	@Column(name = "login_name")
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

	@Temporal(TemporalType.TIMESTAMP)
	public Date getRegistered() {
		return registered;
	}

	public void setRegistered(Date registered) {
		this.registered = registered;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Enumerated(EnumType.STRING)
	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	@OneToOne
	@JoinColumn(name = "credit_id")
	//use "none" to disable foreignKey @link{org.hibernate.mapping.SimpleValue#createForeignKeyOfEntity}
	@ForeignKey(name = "none")
	public Credit getCredit() {
		return credit;
	}

	public void setCredit(Credit credit) {
		this.credit = credit;
	}

	@Override
	public boolean equals(Object o) {
		return EqualsBuilder.reflectionEquals(this, o,
				new String[] { "credit" });
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this,
				new String[] { "credit" });
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
