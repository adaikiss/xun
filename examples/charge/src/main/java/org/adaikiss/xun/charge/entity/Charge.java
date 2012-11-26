/**
 * 
 */
package org.adaikiss.xun.charge.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.adaikiss.xun.charge.validation.constraints.NotZero;
import org.adaikiss.xun.charge.validation.constraints.PropertiesNotNull;
import org.adaikiss.xun.charge.validation.group.ValidationGroup.Save;
import org.adaikiss.xun.charge.validation.group.ValidationGroup.Update;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author hlw
 *
 */
@Entity
@Table(name = "t_charge")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonAutoDetect(fieldVisibility = Visibility.NONE, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE)
public class Charge extends IdEntity {

	@NotNull(groups = Save.class)
	@PropertiesNotNull(propertyNames = "id", groups = Save.class)
	private User user;
	@JsonProperty
	@PropertiesNotNull(propertyNames = "id", groups = {Save.class, Update.class})
	private Category category;
	@JsonProperty
	@NotZero
	private Double amount;
	@JsonProperty
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date date;
	@JsonProperty
	@Length(max = 100)
	private String remark;

	@ManyToOne
	@JoinColumn(name = "user_id")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne
	@JoinColumn(name = "charge_id")
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
