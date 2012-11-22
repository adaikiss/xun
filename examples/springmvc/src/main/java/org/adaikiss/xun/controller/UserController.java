/**
 * 
 */
package org.adaikiss.xun.controller;

import org.adaikiss.xun.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author hlw
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {
	@RequestMapping("/{id}")
	public @ResponseBody User one(@PathVariable Long id){
		User user = new User();
		return user;
	}
}
