/**
 * 
 */
package org.adaikiss.xun.model;

import java.util.Date;

import org.adaikiss.xun.enumeration.Sex;

/**
 * @author hlw
 * 
 */
public class User {
	private Long id;
	private String name;
	private int age;
	private Sex sex;
	private Date birth;
	private Jb jb;

	public User() {

	}

	public User(Long id, String name, int age, Sex sex, Date birth) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.sex = sex;
		this.birth = birth;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public Jb getJb() {
		return jb;
	}

	public void setJb(Jb jb) {
		this.jb = jb;
	}
}
