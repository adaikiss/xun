/**
 * 
 */
package org.adaikiss.xun.designpattern.gof.behavioral.interpreter;

/**
 * @author hlw
 *
 */
public class JournalAccountManager {
	private InterpretContext journalAccountInterpretContext = new JournalAccountInterpretContext();

	public void interpret(String expression){
		String result = new JournalAccountExpression(expression).interpret(journalAccountInterpretContext);
		System.out.println(result);
	}
}
