/**
 * 
 */
package org.adaikiss.xun.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.adaikiss.xun.entity.Credit;
import org.adaikiss.xun.entity.User;
import org.adaikiss.xun.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author hlw
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Override
	public List<User> findAll() {
		List<User> list = new ArrayList<User>();
		for(int i = 0; i < 10; i ++){			
			User u1 = new User();
			u1.setId(1l + i);
			u1.setDisplayName("张三" + i);
			u1.setEmail("z3@gmail.com" + i);
			u1.setLoginName("zhang" + i);
			Credit c1 = new Credit();
			c1.setCredit1(11l + i);
			u1.setCredit(c1);
			list.add(u1);
		}
		return list;
	}

}
