/**
 * 
 */
package org.adaikiss.xun.core.security;

import org.adaikiss.xun.core.entity.Member;
import org.apache.shiro.SecurityUtils;

/**
 * 提供安全相关的方法，在service层使用，与shiro解耦
 * @author HuLingwei
 *
 */
public class SecurityUtil {
	/**
	 * 获取当前登录的会员
	 * @return
	 */
	public static Member getMember(){
		return (Member)SecurityUtils.getSubject().getPrincipal();
	}
}
