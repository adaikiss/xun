/**
 * 
 */
package org.adaikiss.xun.admin.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.adaikiss.xun.cms.entity.Article;
import org.adaikiss.xun.cms.entity.Channel;
import org.adaikiss.xun.cms.repository.ArticleRepository;
import org.adaikiss.xun.cms.service.ArticleService;
import org.adaikiss.xun.core.util.Constant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefaults;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

	@Autowired
	private ArticleService articleService;

	@ResponseBody
	@RequestMapping(value = "/{id}")
	public Article detail(@PathVariable Long id){
		return articleRepository.findOne(id);
	}

	@RequestMapping(value = "/page", produces = {MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody Page<Article> page(@RequestParam("id") Channel channel, @PageableDefaults(value = Constant.DEFAULT_PAGESIZE) Pageable page ){
		return articleRepository.findByChannelOrderByWeightDesc(channel, page);
	}

	@RequestMapping(value = "/add")
	public @ResponseBody Object add(String title, Long channelId, HttpServletRequest request, HttpServletResponse response,
	ModelMap model){
		Map<String, Object> result = new HashMap<String, Object>();
		if(StringUtils.isBlank(title)){
			result.put("success", false);
			result.put("message", "文章标题不能为空!");
			return result;
		}
		try {
			Article article = articleService.add(title, channelId);
			result.put("success", true);
			result.put("data", article);
			return result;
		} catch (Exception e) {
			result.put("success", false);
			result.put("message", e.getMessage());
			return result;
		}
	}
}
