/**
 * 
 */
package org.adaikiss.xun.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.adaikiss.xun.entity.Credit;
import org.adaikiss.xun.entity.QCredit;
import org.springframework.stereotype.Repository;

import com.mysema.query.jpa.impl.JPAQuery;

/**
 * @author hlw
 * 
 */
@Repository
public class CreditRepositoryImpl implements CreditRepositoryQuerydsl {
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Credit> findProletariats(int baseLine) {
		JPAQuery query = new JPAQuery(em);
		QCredit $ = new QCredit("credit");
		query.from($).where($.credit1.lt(baseLine), $.credit2.lt(baseLine),
				$.credit3.lt(baseLine), $.credit4.lt(baseLine),
				$.credit5.lt(baseLine), $.credit6.lt(baseLine),
				$.credit7.lt(baseLine), $.credit8.lt(baseLine));
		return query.list($);

	}
}
