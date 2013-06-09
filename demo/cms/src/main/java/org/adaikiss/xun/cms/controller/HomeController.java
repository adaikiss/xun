/**
 * 
 */
package org.adaikiss.xun.cms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author hlw
 *
 */
@Controller
public class HomeController {
	@RequestMapping("/index")
	public String index(){
		System.out.println("hello world!");
		return "index";
	}
}
