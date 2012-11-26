/**
 * 2011-3-22
 */
package org.adaikiss.xun.charge.security;

import java.util.HashSet;
import java.util.Set;

import org.adaikiss.xun.charge.entity.Permission;
import org.adaikiss.xun.charge.entity.Role;
import org.adaikiss.xun.charge.entity.User;
import org.adaikiss.xun.charge.repository.UserRepository;
import org.adaikiss.xun.enumeration.UserStatus;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.SimpleByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * hlw
 * 
 */
public class Realm extends AuthorizingRealm {

	private static ThreadLocal<User> credential = new ThreadLocal<User>();

	public static User getUser(){
		return credential.get();
	}

	public static String encodePassword(String password, String salt){
		return new Sha256Hash(password, salt).toBase64();
	}

	@Autowired
	private UserRepository userRepository;

	public Realm() {
		setName("Realm");
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		String username = (String) principals.fromRealm(getName()).iterator().next();
		User user = userRepository.findByLoginNameAndStatus(username, UserStatus.normal);
		if (user != null) {
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			Set<String> permSet = new HashSet<String>();
			for (Role role : user.getRoles()) {
				info.addRole(role.getName());
				for(Permission perm:role.getPermissions()){					
					info.addObjectPermission(perm);
				}
			}
			info.addStringPermissions(permSet);
			return info;
		} else {
			return null;
		}
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		User user = userRepository.findByLoginNameAndStatus(upToken.getUsername(), UserStatus.normal);
		if(null == user){
			throw new UnknownAccountException("用户和密码对应的账号不存在!");
		}
		credential.set(user);
		SimpleAuthenticationInfo info =  new SimpleAuthenticationInfo(user.getLoginName(), user.getPassword(), new SimpleByteSource(user.getLoginName()), getName());
		return info;
	}

	/**
	 * 清除用户缓存权限信息
	 * @param principal
	 */
	public void clearCachedAuthorizationInfo(String principal) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
		clearCachedAuthorizationInfo(principals);
	}
}
