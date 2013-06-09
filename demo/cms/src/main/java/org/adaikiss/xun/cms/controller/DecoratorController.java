/**
 * 
 */
package org.adaikiss.xun.cms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author hlw
 * 
 */
@Controller
@RequestMapping("/decorators")
public class DecoratorController {

	private static final String DECORATORS_PATH = "decorators/";

	@RequestMapping("/{decorator}.ftl")
	public String handle(@PathVariable String decorator) {
		return DECORATORS_PATH + decorator;
	}

}
