/**
 * 
 */
package org.adaikiss.xun.mybatis.entity;

import java.util.UUID;

/**
 * @author hlw
 * 
 */
public class Ioo {
	private UUID id;
	private String name;

	public Ioo() {
		super();
	}

	public Ioo(String name) {
		super();
		this.name = name;
	}

	public Ioo(UUID id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
