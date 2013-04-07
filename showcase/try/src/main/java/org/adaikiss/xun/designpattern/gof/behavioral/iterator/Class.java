/**
 * 
 */
package org.adaikiss.xun.designpattern.gof.behavioral.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author hlw
 * 
 */
public class Class implements Iterable<Student>{
	private String name;
	private List<Student> students = new ArrayList<Student>();

	public Class(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addStudent(Student... students) {
		for (Student student : students) {
			this.students.add(student);
		}
	}

	@Override
	public Iterator<Student> iterator() {
		return students.iterator();
	}
}
