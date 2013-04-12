/**
 * 
 */
package org.adaikiss.xun.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import org.adaikiss.xun.entity.User;

/**
 * @author hlw
 * 
 */
@XmlSeeAlso({ User.class })
@XmlRootElement(name = "list")
public class UserListDTO extends ListDTO<User> {
	public UserListDTO() {
		super();
	}

	public UserListDTO(List<User> list) {
		super(list);
	}
}
