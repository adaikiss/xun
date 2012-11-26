/**
 * 
 */
package org.adaikiss.xun.charge.controller;

import org.adaikiss.xun.charge.ajax.AjaxResponse;
import org.adaikiss.xun.charge.security.Realm;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author hlw
 * 
 */
@Controller("login")
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@RequestMapping(value = { "/login" }, method = RequestMethod.POST, produces = {"application/json"})
	public @ResponseBody
	AjaxResponse login(String username, String password, String rememberme) {
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		if (StringUtils.isNotBlank(rememberme)) {
			token.setRememberMe(true);
		}
		try {
			SecurityUtils.getSubject().login(token);
			logger.debug("{}登陆成功!", username);
			return new AjaxResponse(true, "登录成功！", Realm.getUser());
		} catch (AuthenticationException e) {
			return new AjaxResponse(false, "用户名或密码错误!");
		} catch (Exception e) {
			return new AjaxResponse(false, e.getMessage());
		}
	}

	@RequestMapping(value = "/logout")
	@RequiresUser
	public @ResponseBody
	AjaxResponse logout() {
		String username = String.valueOf(SecurityUtils.getSubject().getPrincipal());
		SecurityUtils.getSubject().logout();
		logger.debug("{}退出登陆!", username);
		return new AjaxResponse(true, "退出成功!");
	}
}
