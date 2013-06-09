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
import org.adaikiss.xun.core.ajax.AjaxResponse;
import org.adaikiss.xun.core.util.Constant;
import org.adaikiss.xun.core.util.MessageEchoHelper;
import org.adaikiss.xun.core.validation.group.ValidationGroup;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefaults;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author hlw
 *
 */
@Controller("adminArticleController")
@RequestMapping("/admin/article")
public class ArticleController {
	private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);
	@Autowired
	private ArticleRepository articleRepository;

	@Autowired
	private ArticleService articleService;

	@RequestMapping(value = "/page", produces = {MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody Page<Article> page(@RequestParam("id") Channel channel, @PageableDefaults(value = Constant.DEFAULT_PAGESIZE) Pageable page ){
		return articleRepository.findByChannelOrderByWeightDesc(channel, page);
	}

	@RequestMapping(value = "/create")
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

	@RequestMapping(value = "/update", produces = {MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody Object update(@Validated(ValidationGroup.Update.class)@ModelAttribute("article") Article article, BindingResult result){
		AjaxResponse res = new AjaxResponse();
		if(result.hasErrors()){
			res.setMsg(MessageEchoHelper.echo(result.getAllErrors()));
			return res;
		}
		try {
			articleService.update(article);
			res.setSuccess(true);
			return res;
		} catch (Exception e) {
			res.setMsg(e.getMessage());
			return res;
		}
	}

	@RequestMapping(value = "/read", produces = {MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody Object read(@RequestParam Long id){
		AjaxResponse res = new AjaxResponse();
		Article article = articleRepository.findOne(id);
		if(article == null){
			res.setMsg("文章不存在!");
			return res;
		}
		res.setData(article);
		res.setSuccess(true);
		return res;
	}

	@RequestMapping(value = "/destroy", produces = {MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody Object destroy(@RequestParam Long id){
		AjaxResponse res = new AjaxResponse();
		Article article = articleRepository.findOne(id);
		if(article == null){
			res.setMsg("文章不存在!");
			return res;
		}
		try {
			articleService.delete(article);
			res.setSuccess(true);
			return res;
		} catch (Exception e) {
			logger.error("删除文章时出现异常![id : " + id + "]", e);
			res.setMsg(e.getMessage());
			return res;
		}
	}
}
