/**
 * 
 */
package org.adaikiss.xun.mybatis.entity;

import java.io.Serializable;

/**
 * @author hlw
 * 
 */
public class Moo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1164822600924625853L;
	private Long id;
	private String name;

	public Moo() {
		super();
	}

	public Moo(String name) {
		super();
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
