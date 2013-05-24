/**
 * 
 */
package org.adaikiss.xun.core.repository;

import org.adaikiss.xun.core.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author hlw
 * 
 */
public interface MemberRepository extends JpaRepository<Member, Long>{
	Member findByLoginName(String loginName);
}
