/**
 * 
 */
package org.adaikiss.xun.mybatis.entity;

import java.util.Date;

/**
 * @author hlw
 *
 */
public class Aoo {
	private Long id;
	private String name;
	private Date createTime;

	public Aoo() {
		super();
	}

	public Aoo(String name, Date createTime) {
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
