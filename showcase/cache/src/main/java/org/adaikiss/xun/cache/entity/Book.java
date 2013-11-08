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
public class Book implements Serializable{
	private Long id;
	private String name;
	private String isbn;
	private Category category;
	private String content;
	private int times;

	public Book(Long id, String name, String isbn, Category category,
			String content) {
		super();
		this.id = id;
		this.name = name;
		this.isbn = isbn;
		this.category = category;
		this.content = content;
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

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getTimes() {
		return times;
	}

	public void setTimes(int times) {
		this.times = times;
	}

	public void increaseTimes(){
		times++;
	}
}
