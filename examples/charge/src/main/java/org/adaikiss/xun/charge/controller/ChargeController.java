/**
 * 
 */
package org.adaikiss.xun.charge.controller;

import java.util.List;

import org.adaikiss.xun.charge.entity.Charge;
import org.adaikiss.xun.charge.entity.User;
import org.adaikiss.xun.charge.repository.ChargeRepository;
import org.adaikiss.xun.charge.service.ChargeService;
import org.adaikiss.xun.charge.validation.group.ValidationGroup.Save;
import org.adaikiss.xun.charge.validation.group.ValidationGroup.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

	@Autowired
	private ChargeService chargeService;

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public @ResponseBody Charge add(@Validated(Save.class)Charge charge){
		charge = chargeService.save(charge);
		return charge;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public @ResponseBody Charge update(@Validated(Update.class) Charge charge){
		return charge;
	}

	@RequestMapping(value = {"/list", "/list.*"}, method = RequestMethod.GET)
	public @ResponseBody List<Charge> all(){
		User user = new User();
		user.setId(1l);
		return chargeRepository.findByUserOrderByDateDesc(user);
	}

	@RequestMapping(value = {"/page", "/page.*"}, method = RequestMethod.GET)
	public @ResponseBody Page<Charge> page(@RequestParam(defaultValue = "1")int page, int limit){
		User user = new User();
		user.setId(1l);
		Pageable pageRequest = new PageRequest(page - 1, limit);
		return chargeRepository.findByUserOrderByDateDesc(user, pageRequest);
	}
}
