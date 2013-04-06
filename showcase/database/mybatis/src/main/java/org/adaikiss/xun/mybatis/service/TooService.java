/**
 * 
 */
package org.adaikiss.xun.mybatis.service;

import org.adaikiss.xun.mybatis.entity.Too;
import org.adaikiss.xun.mybatis.entity.TooMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author hlw
 *
 */
@Service
@Transactional
public class TooService {
	@Autowired
	private TooMapper mapper;

	public Long add(Too too){
		mapper.insert(too);
		if("rollback".equals(too.getName())){
			throw new RuntimeException("you should rollback!");
		}
		return too.getId();
	}

	@Transactional(readOnly = true)
	public Too get(Long id){
		return mapper.selectById(id);
	}
}
