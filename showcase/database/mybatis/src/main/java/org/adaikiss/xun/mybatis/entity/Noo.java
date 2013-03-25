/**
 * 
 */
package org.adaikiss.xun.mybatis.entity;

/**
 * @author hlw
 *
 */
public class Noo {
	private Long id;
	private String name;

	public Noo() {
		super();
	}

	public Noo(String name) {
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
