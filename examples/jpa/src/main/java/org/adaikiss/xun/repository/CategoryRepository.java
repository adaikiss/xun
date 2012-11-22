/**
 * 
 */
package org.adaikiss.xun.repository;

import org.adaikiss.xun.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author hlw
 *
 */
public interface CategoryRepository extends JpaRepository<Category, Long>,
		JpaSpecificationExecutor<Category> {
	
}
