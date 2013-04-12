/**
 * 
 */
package org.adaikiss.xun.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.adaikiss.xun.dto.ListDTO;
import org.adaikiss.xun.entity.User;
import org.adaikiss.xun.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author hlw
 * 
 */
@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.TEXT_XML_VALUE })
	public @ResponseBody
	User detail(HttpServletResponse response, @PathVariable Long id) {
		User user = userRepository.get(id);
		return user;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = { MediaType.TEXT_HTML_VALUE })
	public String detail(HttpServletResponse response, @PathVariable Long id,
			Model model) throws IOException {
		User user = userRepository.get(id);
		if (null == user) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		model.addAttribute("user", user);
		return "user/show";
	}

	@RequestMapping(method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.TEXT_XML_VALUE })
	public @ResponseBody
	ListDTO<User> list() {
		List<User> list = userRepository.list();
		return new ListDTO<User>(list);
	}

	@RequestMapping(method = RequestMethod.GET, produces = { MediaType.TEXT_HTML_VALUE })
	public String list(Model model) {
		List<User> list = userRepository.list();
		model.addAttribute("list", list);
		return "user/list";
	}

	@RequestMapping(value = "/add")
	public String add() {
		return "user/create";
	}

	@RequestMapping(method = RequestMethod.PUT)
	public String create(@Valid User user) {
		userRepository.add(user);
		return "redirect:/user/" + user.getId();
	}

	@RequestMapping("/{id}/edit")
	public String edit(HttpServletResponse response, Model model,
			@PathVariable Long id) throws IOException {
		User user = userRepository.get(id);
		if (null == user) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		model.addAttribute("user", user);
		return "user/update";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public String save(@Valid User user) {
		userRepository.update(user);
		return "redirect:/user/" + user.getId();
	}
}
