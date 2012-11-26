/**
 * 
 */
package org.adaikiss.xun.charge.service;

import java.util.Date;

import org.adaikiss.xun.charge.entity.Category;
import org.adaikiss.xun.charge.entity.Charge;
import org.adaikiss.xun.charge.entity.User;
import org.adaikiss.xun.charge.repository.CategoryRepository;
import org.adaikiss.xun.charge.repository.ChargeRepository;
import org.adaikiss.xun.charge.repository.UserRepository;
import org.adaikiss.xun.utils.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author hlw
 *
 */
@Service
@Transactional
public class ChargeService {
	@Autowired
	ChargeRepository chargeRepository;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	UserRepository userRepository;

	@Transactional(readOnly = true)
	public Charge save(Charge charge){
		charge.setDate(new Date());
		User user = userRepository.findOne(charge.getUser().getId());
		if(null == user){
			throw new ServiceException("用户不存在！");
		}
		if(charge.getCategory() != null){			
			Category category = categoryRepository.findOne(charge.getCategory().getId());
			if(null == category){
				throw new ServiceException("类别不存在！");
			}
			if(category.getUser().getId() != user.getId()){
				throw new ServiceException("该类别和用户不匹配！");
			}
			charge.setCategory(category);
		}
		charge.setUser(user);
		return chargeRepository.save(charge);
	}
}
