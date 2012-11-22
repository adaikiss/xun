/**
 * 
 */
package org.adaikiss.xun.predicate;

import org.adaikiss.xun.entity.QCredit;

import com.mysema.query.types.expr.BooleanExpression;


/**
 * @author hlw
 * 
 */
public class CreditPredicates {
	private static QCredit $ = QCredit.credit;

	public static BooleanExpression isProletariat(int baseLine) {
		return $.credit1.lt(baseLine).and($.credit2.lt(baseLine))
				.and($.credit3.lt(baseLine)).and($.credit4.lt(baseLine))
				.and($.credit5.lt(baseLine)).and($.credit6.lt(baseLine))
				.and($.credit7.lt(baseLine)).and($.credit8.lt(baseLine));
	}
}
