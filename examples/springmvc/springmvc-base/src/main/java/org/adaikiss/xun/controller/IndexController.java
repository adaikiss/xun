/**
 * 
 */
package org.adaikiss.xun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author hlw
 *
 */
@Controller
public class IndexController {

	@RequestMapping("/index")
	public String index(){
		return "freemarker/index";
	}
}
