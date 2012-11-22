/**
 * 
 */
package org.adaikiss.xun.charge.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author hlw
 * 
 */
@Entity
@Table(name = "t_user_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonInclude(Include.NON_EMPTY)
public class UserInfo extends IdEntity {
	private User user;
	private String province;
	private String city;
	private String county;
	private String email;

	@OneToOne(optional = false)
	@JoinColumn(name = "user_id")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Transient
	public String getAddress(){
		if(StringUtils.equals(province, city)){
			return city + county;
		}
		return province + city + county;
	}
}
