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
public class ShowcaseController {
	@RequestMapping("/showcase")
	public String showcase() {
		return "showcase";
	}
}
