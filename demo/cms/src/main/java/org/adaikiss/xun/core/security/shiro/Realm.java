package org.adaikiss.xun.core.security.shiro;

import java.util.HashSet;
import java.util.Set;

import org.adaikiss.xun.core.entity.Employee;
import org.adaikiss.xun.core.entity.Member;
import org.adaikiss.xun.core.entity.Permission;
import org.adaikiss.xun.core.entity.Role;
import org.adaikiss.xun.core.repository.EmployeeRepository;
import org.adaikiss.xun.core.repository.MemberRepository;
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

	public static String encodePassword(String password, String salt){
		return new Sha256Hash(password, salt).toBase64();
	}

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	public Realm() {
		setName("Realm");
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		Member member = (Member) principals.fromRealm(getName()).iterator().next();
		if(member.isEmployee()){
			Employee employee = employeeRepository.findOne(member.getId());
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			Set<String> permSet = new HashSet<String>();
			for (Role role : employee.getRoles()) {
				info.addRole(role.getName());
				for(Permission perm:role.getPermissions()){
					info.addObjectPermission(perm);
				}
			}
			info.addStringPermissions(permSet);
			return info;
		}
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		Member member = memberRepository.findByLoginName(upToken.getUsername());
		if(null == member){
			throw new UnknownAccountException("用户和密码对应的账号不存在!");
		}
		SimpleAuthenticationInfo info =  new SimpleAuthenticationInfo(member, member.getPassword(), new SimpleByteSource(member.getLoginName()), getName());
		return info;
	}

	/**
	 * 清除用户缓存权限信息
	 * @param principal
	 */
	public void clearCachedAuthorizationInfo(Member principal) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
		clearCachedAuthorizationInfo(principals);
	}
}
