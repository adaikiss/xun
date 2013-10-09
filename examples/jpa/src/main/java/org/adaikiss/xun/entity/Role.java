/**
 * 
 */
package org.adaikiss.xun.entity;

import javax.persistence.Entity;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author hlw
 * 
 */
@Entity
public class Role extends IdEntity {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		return EqualsBuilder.reflectionEquals(this, o,
				new String[] {});
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this,
				new String[] {});
	}

	@Override
	public String toString(){
		return ToStringBuilder.reflectionToString(this);
	}
}
