/**
 * 
 */
package org.adaikiss.xun.datatransfer.msgpack;

import org.msgpack.annotation.Optional;

/**
 * @author hlw
 *
 */
@org.msgpack.annotation.Message
public class User {
	private String name;
	private boolean system;
	@Optional
	private String password;

	public User() {
		super();
	}

	public User(String name, boolean system, String password) {
		super();
		this.name = name;
		this.system = system;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isSystem() {
		return system;
	}

	public void setSystem(boolean system) {
		this.system = system;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
