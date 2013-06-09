/**
 * 
 */
package org.adaikiss.xun.cms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.adaikiss.xun.cms.entity.Article;
import org.adaikiss.xun.cms.enumeration.PostStatus;
import org.adaikiss.xun.cms.repository.ArticleRepository;
import org.adaikiss.xun.cms.service.ArticleService;
import org.adaikiss.xun.core.util.FrontUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author hlw
 *
 */
@Controller("articleController")
public class ArticleController {

	@Autowired
	private ArticleService articleService;

	@Autowired
	private ArticleRepository articleRepository;

	@RequestMapping(value = {"/{id}.html"})
	public String index(@PathVariable Long id, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		Article article = articleRepository.findByIdAndStatus(id, PostStatus.Normal);
		if (null == article) {
			return FrontUtil.pageNotFound(request, response, model);
		}
		model.addAttribute("article", article);
		String tpl = article.getRealTpl();
		if (StringUtils.isBlank(tpl)) {
			return FrontUtil.getDefaultArticleTpl();
		}
		return tpl;
	}
}
