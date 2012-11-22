/**
 * 
 */
package org.adaikiss.xun.repository;

import java.util.Random;
import java.util.Set;
import java.util.UUID;

import org.adaikiss.xun.entity.Permission;
import org.adaikiss.xun.entity.Role;
import org.adaikiss.xun.entity.User;
import org.adaikiss.xun.enumeration.UserStatus;
import org.adaikiss.xun.security.Realm;

import com.google.common.collect.Sets;

/**
 * @author hlw
 * 
 */
public class UserRepository  {
	public User findByLoginNameAndStatus(String loginName, UserStatus status){
		User user = new User();
		UUID id = UUID.randomUUID();
		user.setId(new Random().nextLong());
		user.setLoginName(loginName);
		user.setName(id.toString());
		Role role1 = new Role("admin");
		Role role2 = new Role("guest");
		role1.addPermissions(new Permission("*"));
		role2.addPermissions(new Permission("article:read"));
		Set<Role> roles = Sets.newHashSet(role2);
		if(loginName.equals("admin")){
			roles.add(role1);
		}
		user.setRoles(roles);
		user.setPassword(Realm.encodePassword("123456", loginName));
		return user;
	}
}
