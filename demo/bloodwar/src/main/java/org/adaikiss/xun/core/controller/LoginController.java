/**
 * 
 */
package org.adaikiss.xun.core.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.adaikiss.xun.core.ajax.AjaxResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author hlw
 *
 */
@Controller
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	@RequestMapping(value = "/admin/login", method = RequestMethod.GET)
	public String loginAdmin(){
		return "admin/login";
	}
	@RequestMapping(value = "/admin/login", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
	public @ResponseBody AjaxResponse loginAdmin(HttpServletRequest request, HttpServletResponse response, @RequestParam String username, @RequestParam String password, @RequestParam(defaultValue = "false") boolean rememberMe){
		AjaxResponse res = new AjaxResponse();
//		try {
//			logger.debug("用户登录 username:{}, password:{}, rememberMe:{}", username, password, rememberMe);
//			SecurityUtils.getSubject().login(new UsernamePasswordToken(username, password, rememberMe));
//			res.setSuccess(true);
//		} catch(AuthenticationException e){
//			logger.debug("用户登录 username:{}, password:{}, rememberMe:{} 失败!", username, password, rememberMe);
//			res.setMsg("账号或密码错误!");
//		}
		return res;
	}

	@RequestMapping(value = "/admin/logout", method = RequestMethod.GET)
	public String logoutAdmin(){
//		SecurityUtils.getSubject().logout();
		return "admin/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(){
		return "login";
	}

	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, HttpServletResponse response, @RequestParam String username, @RequestParam String password, @RequestParam(defaultValue = "false") boolean rememberMe){
//		try {
//			logger.debug("用户登录 username:{}, password:{}, rememberMe:{}", username, password, rememberMe);
//			SecurityUtils.getSubject().login(new UsernamePasswordToken(username, password, rememberMe));
//			WebUtils.redirectToSavedRequest(request, response, "/");
//			return null;
//		} catch (IOException e) {
//			logger.warn("跳转到登录前访问页面时出现异常!", e);
//			return null;
//		} catch(AuthenticationException e){
//			logger.debug("用户登录 username:{}, password:{}, rememberMe:{} 失败!", username, password, rememberMe);
			return "login";
//		}
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(){
//		SecurityUtils.getSubject().logout();
		return "login";
	}
}
