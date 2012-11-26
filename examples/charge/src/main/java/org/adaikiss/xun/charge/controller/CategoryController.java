/**
 * 
 */
package org.adaikiss.xun.charge.controller;

import java.util.List;

import org.adaikiss.xun.charge.ajax.AjaxFilter;
import org.adaikiss.xun.charge.ajax.AjaxResponse;
import org.adaikiss.xun.charge.entity.Category;
import org.adaikiss.xun.charge.entity.User;
import org.adaikiss.xun.charge.repository.CategoryRepository;
import org.adaikiss.xun.charge.security.Realm;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/category")
public class CategoryController {
	@Autowired
	private CategoryRepository categoryRepository;

	@RequestMapping(value = {"/list", "/list.*"}, method = RequestMethod.GET)
	public @ResponseBody
	List<Category> all() {
		return categoryRepository.findByUser(Realm.getUser());
	}
}
