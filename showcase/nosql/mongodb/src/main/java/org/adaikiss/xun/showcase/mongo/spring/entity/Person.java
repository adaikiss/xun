/**
 * 
 */
package org.adaikiss.xun.showcase.mongo.spring.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.data.annotation.Id;

/**
 * @author HuLingwei
 * 
 */
public class Person {
	@Id
	private String id;
	private String name;
	private int age;
	private Date birth;

	public Person() {
		super();
	}

	public Person(String name) {
		super();
		this.name = name;
	}

	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public Person(String name, int age, Date birth) {
		super();
		this.name = name;
		this.age = age;
		this.birth = birth;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	@Override
	public String toString() {
		return new StringBuilder().append("Person [id=").append(id)
				.append(", name=").append(name).append(", age=").append(age)
				.append(", birth=")
				.append(new SimpleDateFormat("yyyy-MM-dd").format(birth))
				.append("]").toString();
	}
}
