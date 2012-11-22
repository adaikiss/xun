/**
 * 
 */
package org.adaikiss.xun.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.adaikiss.xun.entity.Category;
import org.springframework.data.jpa.domain.Specification;

/**
 * @author hlw
 *
 */
public class CategorySpecifications {
	public static Specification<Category> topLevelAndDescriptionLongerThan(final int length){
		return new Specification<Category>(){
			@Override
			public Predicate toPredicate(Root<Category> root, CriteriaQuery<?> query, CriteriaBuilder cb){
				Predicate categoryIsTopLevel = cb.isNull(root.<Category>get("parent"));
				Predicate categoryDescriptionLongerThan = cb.gt(cb.length(root.<String> get("description")), length);
				return cb.and(categoryIsTopLevel, categoryDescriptionLongerThan);
			}
		};
	}

}
