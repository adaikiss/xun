/**
 * 
 */
package org.adaikiss.xun.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.adaikiss.xun.entity.User;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * @author hlw
 * 
 */
@Controller
@RequestMapping("/user")
public class UserController {
	@RequestMapping("/{id}")
	public @ResponseBody
	User one(@PathVariable Long id) {
		User user = new User();
		user.setId(id);
		user.setDisplayName("从来都没有什么救世主");
		user.setLoginName("adaikiss");
		user.setEmail("adaikiss@163.com");
		user.setNiceName("敏");
		return user;
	}

	@RequestMapping(value = "/list")
	public @ResponseBody
	List<User> list() {
		List<User> list = new ArrayList<User>();
		for (int i = 0; i < 5; i++) {
			User user = new User();
			user.setId(((Integer) i).longValue());
			user.setDisplayName("从来都没有什么救世主");
			user.setLoginName("adaikiss");
			user.setEmail("adaikiss@163.com");
			user.setNiceName("敏");
			list.add(user);
		}
		return list;
	}
}
