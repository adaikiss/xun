/**
 * 
 */
package org.adaikiss.xun.cache.entity;

import java.io.Serializable;

/**
 * @author HuLingwei
 *
 */
@SuppressWarnings("serial")
public class Category implements Serializable{
	private Long id;
	private String name;

	public Category(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
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
}
