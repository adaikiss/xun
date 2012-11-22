/**
 * 
 */
package org.adaikiss.xun.charge.controller;

import java.util.List;

import org.adaikiss.xun.charge.entity.User;
import org.adaikiss.xun.charge.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author hlw
 *
 */
@Controller("user")
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value = {"/{id}", "/{id}.*"}, method = RequestMethod.GET)
	public @ResponseBody User one(@PathVariable Long id){
		User user = userRepository.findOne(id);
		return user;
	}

	@RequestMapping(value = {"/list", "/list.*"}, method = RequestMethod.GET)
	public @ResponseBody List<User> all(){
		List<User> users = userRepository.findAll();
//		ParameterizedTypeReference<List<User>> listOfUsers = new ParameterizedTypeReference<List<User>>(){
//		};
		return users;
	}

}
