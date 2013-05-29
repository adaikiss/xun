/**
 * 
 */
package org.adaikiss.xun.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.adaikiss.xun.dto.ListDTO;
import org.adaikiss.xun.entity.User;
import org.adaikiss.xun.repository.UserRepository;
import org.adaikiss.xun.utils.MessageEchoHelper;
import org.adaikiss.xun.validation.group.Create;
import org.adaikiss.xun.validation.group.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

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
	public String create(@Validated({Create.class}) User user, BindingResult result, Model model) {
		if(result.hasErrors()){
			Map<String, Object> errors = MessageEchoHelper.reform(result.getFieldErrors());
			model.addAttribute("errors", errors);
			return add();
		}
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
	public String save(@Validated({Update.class}) User user) {
		userRepository.update(user);
		return "redirect:/user/" + user.getId();
	}

	@ResponseStatus(HttpStatus.SEE_OTHER)
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public View delete(@PathVariable Long id) {
		userRepository.delete(id);
		RedirectView view = new RedirectView("/user", true);
		view.setStatusCode(HttpStatus.SEE_OTHER);
		return view;
	}

//	@RequestMapping(value = "/batch", method = RequestMethod.GET, produces = {
//			MediaType.APPLICATION_JSON_VALUE})
//	public @ResponseBody
//	ListDTO<User> batch(@RequestParam("users") ArrayList<User> users) {
//		List<User> list = users;
//		return new ListDTO<User>(list);
//	}

	//http://static.springsource.org/spring-data/data-jpa/docs/current/reference/html/repositories.html#web-pagination
	@RequestMapping(value = "/page", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody
	Pageable page(@PageableDefaults(value = 20)Pageable page) {
		return page;
	}

	@RequestMapping(value = "/batch1", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody
	ListDTO<String> batch1(@RequestParam("users") ArrayList<String> users) {
		List<String> list = users;
		return new ListDTO<String>(list);
	}
}
