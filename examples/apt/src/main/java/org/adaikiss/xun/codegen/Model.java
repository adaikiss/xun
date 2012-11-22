/**
 * 
 */
package org.adaikiss.xun.codegen;

/**
 * @author hlw
 *
 */
public abstract class Model {
	protected String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public abstract Model collect();
}
