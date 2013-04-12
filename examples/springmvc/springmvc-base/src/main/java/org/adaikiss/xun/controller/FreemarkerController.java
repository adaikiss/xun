/**
 * 
 */
package org.adaikiss.xun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author hlw
 *
 */
@Controller
@RequestMapping("/freemarker")
public class FreemarkerController {
	@RequestMapping("/hello")
	public String hello(Model m){
		m.addAttribute("msg", "Hello, freemarker!");
		return "hello";
	}

	@RequestMapping("hi")
	public String hi(Model m){
		m.addAttribute("msg", "Hi, freemarker!");
		return "freemarker/hi";
	}
}
