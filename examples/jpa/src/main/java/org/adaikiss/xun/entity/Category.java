/**
 * 
 */
package org.adaikiss.xun.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author hlw
 * 
 */
@Entity
public class Category extends IdEntity {

	private String name;
	private String slug;
	private String description;
	private Category parent;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@ManyToOne
	@JoinColumn(name = "parent_id")
	public Category getParent() {
		return parent;
	}

	public void setParent(Category parent) {
		this.parent = parent;
	}

	@Override
	public boolean equals(Object o){
		return EqualsBuilder.reflectionEquals(this, o, new String[]{"parent"});
	}

	@Override
	public int hashCode(){
		return HashCodeBuilder.reflectionHashCode(this, new String[]{"parent"});
	}
}
