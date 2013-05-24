/**
 * 
 */
package org.adaikiss.xun.core.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.adaikiss.xun.core.util.Constant;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 会员
 * 
 * @author hlw
 * 
 */
@Entity
@Table(name = Constant.TABLE_PREFIX + "member")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonAutoDetect(fieldVisibility = Visibility.NONE, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE)
@JsonInclude(Include.NON_EMPTY)
public class Member extends User {
	/**
	 * 是否为职员
	 */
	private boolean employee;

	public boolean isEmployee() {
		return employee;
	}

	public void setEmployee(boolean employee) {
		this.employee = employee;
	}
}
