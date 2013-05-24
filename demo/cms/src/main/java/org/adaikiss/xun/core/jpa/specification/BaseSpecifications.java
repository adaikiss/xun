/**
 * 
 */
package org.adaikiss.xun.core.jpa.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.orm.PropertyFilter.MatchType;

/**
 * @author hlw
 * 
 */
public class BaseSpecifications {
	public static <T> Specification<T> propertyFilter(
			final List<PropertyFilter> filters) {
		return new Specification<T>() {
			@SuppressWarnings("unchecked")
			protected <X> Predicate buildPredicate(
					final String propertyName, final X propertyValue,
					final MatchType matchType, Root<T> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				if (!StringUtils.hasText(propertyName)) {
					throw new IllegalArgumentException("propertyName不能为空");
				}
				Predicate predicate = null;
				String[] propertyNames = propertyName.split("\\.");
				Path<X> property;
				if(propertyNames.length == 1){
					property = root.get(propertyName);
				}else{
					//点值关联查询
					Path<Object> p = root.get(propertyNames[0]);
					for(int i = 1; i < propertyNames.length; i ++){
						p = p.get(propertyNames[i]);
					}
					property = (Path<X>)p;
				}
				// 根据MatchType构造predicate
				switch (matchType) {
				case EQ:
					predicate = cb.equal(property, propertyValue);
					break;
				case NEQ:
					predicate = cb.notEqual(property, propertyValue);
					break;
				case LIKE:
					predicate = cb.like(property.as(String.class),
							(String) propertyValue);
					break;
				default:
					//@SuppressWarnings("unchecked")
					Expression<Comparable<Object>> v = (Expression<Comparable<Object>>) property;
					//@SuppressWarnings("unchecked")
					Comparable<Object> p = (Comparable<Object>) propertyValue;
					switch (matchType) {
					case LE:
						predicate = cb.lessThanOrEqualTo(v, p);
						break;
					case LT:
						predicate = cb.lessThan(v, p);
						break;
					case GE:
						predicate = cb.greaterThanOrEqualTo(v, p);
						break;
					case GT:
						predicate = cb.greaterThan(v, p);
						break;
					}
					break;
				}
				return predicate;
			}

			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				List<Predicate> ps = new ArrayList<Predicate>();
				for (PropertyFilter filter : filters) {
					if (!filter.hasMultiProperties()) { // 只有一个属性需要比较的情况.
						Predicate predicate = buildPredicate(
								filter.getPropertyName(),
								filter.getMatchValue(), filter.getMatchType(),
								root, query, cb);
						ps.add(predicate);
					} else {// 包含多个属性需要比较的情况,进行or处理.
						List<Predicate> predicates = new ArrayList<Predicate>();
						for (String param : filter.getPropertyNames()) {
							Predicate predicate = buildPredicate(param,
									filter.getMatchValue(),
									filter.getMatchType(), root, query, cb);
							predicates.add(predicate);
						}
						ps.add(cb.or(predicates.toArray(new Predicate[0])));
					}
				}
				return cb.and(ps.toArray(new Predicate[0]));
			}
		};
	}
}
