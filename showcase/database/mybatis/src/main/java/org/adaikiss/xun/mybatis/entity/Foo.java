/**
 * 
 */
package org.adaikiss.xun.mybatis.entity;

import java.util.List;

/**
 * @author hlw
 * 
 */
public class Foo {
	private Long id;
	private String name;
	private Aoo aoo;
	private List<Boo> boos;
	private List<Coo> coos;

	public Foo() {
		super();
	}

	public Foo(String name) {
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

	public Aoo getAoo() {
		return aoo;
	}

	public void setAoo(Aoo aoo) {
		this.aoo = aoo;
	}

	public List<Boo> getBoos() {
		return boos;
	}

	public void setBoos(List<Boo> boos) {
		this.boos = boos;
	}

	public List<Coo> getCoos() {
		return coos;
	}

	public void setCoos(List<Coo> coos) {
		this.coos = coos;
	}

}
