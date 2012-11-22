/**
 * 
 */
package org.adaikiss.xun.repository;

import org.adaikiss.xun.entity.Credit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

/**
 * @author hlw
 *
 */
public interface CreditRepository extends JpaRepository<Credit, Long>, QueryDslPredicateExecutor<Credit>, CreditRepositoryQuerydsl{

}
