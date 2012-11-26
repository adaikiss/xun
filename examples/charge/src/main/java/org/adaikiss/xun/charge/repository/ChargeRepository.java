/**
 * 
 */
package org.adaikiss.xun.charge.repository;

import java.util.List;

import org.adaikiss.xun.charge.entity.Charge;
import org.adaikiss.xun.charge.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author hlw
 * 
 */
public interface ChargeRepository extends JpaRepository<Charge, Long> {
	List<Charge> findByUserOrderByDateDesc(User user);

	@Query("select c from Charge c where c.user.id = :userId order by c.date desc")
	List<Charge> findByUserIdOrderByDateDesc(@Param("userId") Long userId);

	Page<Charge> findByUserOrderByDateDesc(User user, Pageable pageRequest);
}
