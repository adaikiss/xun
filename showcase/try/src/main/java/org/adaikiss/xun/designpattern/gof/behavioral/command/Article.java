/**
 * 
 */
package org.adaikiss.xun.designpattern.gof.behavioral.command;

/**
 * @author hlw
 *
 */
public class Article implements Operable {
	private String name;
	public Article(String name){
		this.name = name;
	}

	@Override
	public String toString() {
		return "article[" + name + "]";
	}
	
}
