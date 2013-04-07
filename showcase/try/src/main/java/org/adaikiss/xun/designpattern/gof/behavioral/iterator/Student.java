/**
 * 
 */
package org.adaikiss.xun.designpattern.gof.behavioral.iterator;

/**
 * @author hlw
 * 
 */
public class Student {
	private int no;
	private String name;

	public Student(int no, String name) {
		super();
		this.no = no;
		this.name = name;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString(){
		return "No:" + no + ", name:" + name;
	}
}
