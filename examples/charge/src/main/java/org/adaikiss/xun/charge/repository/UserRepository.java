/**
 * 
 */
package org.adaikiss.xun.charge.repository;

import org.adaikiss.xun.charge.entity.User;
import org.adaikiss.xun.enumeration.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author hlw
 * 
 */
public interface UserRepository extends JpaRepository<User, Long> {
	public User findByLoginNameAndStatus(String loginName, UserStatus status);
}
