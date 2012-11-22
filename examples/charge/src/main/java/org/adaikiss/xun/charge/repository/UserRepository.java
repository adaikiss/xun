/**
 * 
 */
package org.adaikiss.xun.charge.repository;

import org.adaikiss.xun.charge.entity.User;
import org.adaikiss.xun.enumeration.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author hlw
 * 
 */
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {
	@Transactional(readOnly = true)
	public User findByLoginNameAndStatus(String loginName, UserStatus status);
}
