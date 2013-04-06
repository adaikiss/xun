/**
 * 
 */
package org.adaikiss.xun.mybatis.entity;

import java.util.Date;

/**
 * @author hlw
 * 
 */
public class Noo {
	private Long id;
	private String name;
	private Integer size;
	private Date createTime;

	public Noo() {
		super();
	}

	public Noo(String name) {
		this.name = name;
	}

	public Noo(String name, Date createTime) {
		super();
		this.name = name;
		this.createTime = createTime;
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

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
