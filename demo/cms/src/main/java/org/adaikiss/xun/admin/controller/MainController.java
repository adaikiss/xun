/**
 * 
 */
package org.adaikiss.xun.admin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author HuLingwei
 *
 */
@Controller
@RequestMapping("/admin")
public class MainController {
	private static final Logger logger = LoggerFactory
			.getLogger(MainController.class);

	@RequestMapping("/main")
	public String index(Model model){
		return "main";
	}
}
