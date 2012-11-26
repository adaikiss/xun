/**
 * 
 */
package org.adaikiss.xun.charge.repository;

import java.util.List;

import org.adaikiss.xun.charge.entity.Category;
import org.adaikiss.xun.charge.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author hlw
 * 
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
	public List<Category> findByUser(User user);
}
