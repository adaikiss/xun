/**
 * 
 */
package org.adaikiss.xun.admin.controller;

import org.adaikiss.xun.cms.entity.Article;
import org.adaikiss.xun.cms.repository.ArticleRepository;
import org.adaikiss.xun.core.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author hlw
 *
 */
@Controller
@RequestMapping("/article")
public class ArticleController {
	@Autowired
	private ArticleRepository articleRepository;

	@ResponseBody
	@RequestMapping(value = "/{id}")
	public Article detail(@PathVariable Long id){
		return articleRepository.findOne(id);
	}

	@ResponseBody
	@RequestMapping(value = "/page")
	public Page<Article> page(@PageableDefaults(value = Constant.DEFAULT_PAGESIZE) PageRequest page ){
		return articleRepository.findAll(page);
	}
}
