/**
 * 
 */
package org.adaikiss.xun.showcase.redis.jedis.dto;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * @author HuLingwei
 * 
 */
@SuppressWarnings("serial")
public class JavaDTO implements Serializable {
	private Long id;
	private String name;
	private Date date;

	public JavaDTO() {

	}

	public JavaDTO(Long id, String name, Date date) {
		super();
		this.id = id;
		this.name = name;
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public boolean equals(Object another) {
		return EqualsBuilder.reflectionEquals(this, another, new String[0]);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, false);
	}
}
