/**
 * 
 */
package org.adaikiss.xun.designpattern.gof.behavioral.interpreter;

/**
 * TYPE|AMOUNT|DESCRIPTION
 * @author hlw
 * 
 */
public class JournalAccountExpression extends Expression {
	private String expression;

	public JournalAccountExpression(String expression) {
		this.expression = expression;
	}

	@Override
	public String interpret(InterpretContext interpretContext) {
		JournalAccountInterpretContext context = (JournalAccountInterpretContext)interpretContext;
		String split = context.getSplit();
		String type, description;
		double amount;
		String[] array = expression.split(split);
		type = array[0];
		amount = Double.parseDouble(array[1]);
		description = array[2];
		return new StringBuilder("Type:").append(type).append(", Amount:").append(amount).append(", Description:").append(description).toString();
	}

}
