/**
 * 
 */
package org.adaikiss.xun.charge.controller;

import java.util.List;

import org.adaikiss.xun.charge.entity.Charge;
import org.adaikiss.xun.charge.entity.User;
import org.adaikiss.xun.charge.repository.ChargeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author hlw
 *
 */
@Controller("charge")
@RequestMapping("/charge")
public class ChargeController {

	@Autowired
	private ChargeRepository chargeRepository;

	@RequestMapping(value = {"/list", "/list.*"}, method = RequestMethod.GET)
	public @ResponseBody List<Charge> all(){
		User user = new User();
		user.setId(1l);
		return chargeRepository.findByUserOrderByDateDesc(user);
	}
}
