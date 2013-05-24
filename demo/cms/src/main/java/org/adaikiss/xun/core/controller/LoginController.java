/**
 * 
 */
package org.adaikiss.xun.core.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author hlw
 *
 */
@Controller
@RequestMapping("/login")
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	@RequestMapping(method = RequestMethod.GET)
	public String login(){
		return "login";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String login(HttpServletRequest request, HttpServletResponse response, @RequestParam String username, @RequestParam String password, @RequestParam boolean rememberMe){
		try {
			logger.debug("用户登录 username:{}, password:{}, rememberMe:{}", username, password, rememberMe);
			SecurityUtils.getSubject().login(new UsernamePasswordToken(username, password, rememberMe));
			WebUtils.redirectToSavedRequest(request, response, "/");
			return null;
		} catch (IOException e) {
			logger.warn("跳转到登录前访问页面时出现异常!", e);
			return null;
		} catch(AuthenticationException e){
			logger.debug("用户登录 username:{}, password:{}, rememberMe:{} 失败!", username, password, rememberMe);
			return "login";
		}
	}
}
