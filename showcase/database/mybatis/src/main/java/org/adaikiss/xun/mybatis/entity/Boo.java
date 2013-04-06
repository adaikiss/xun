/**
 * 
 */
package org.adaikiss.xun.mybatis.entity;

/**
 * @author hlw
 *
 */
public class Boo {
	private Long id;
	private String name;
	private Long fooId;

	public Boo() {
		super();
	}

	public Boo(String name) {
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

	public Long getFooId() {
		return fooId;
	}

	public void setFooId(Long fooId) {
		this.fooId = fooId;
	}
}
