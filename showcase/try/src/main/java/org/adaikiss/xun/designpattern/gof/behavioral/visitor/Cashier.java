/**
 * 
 */
package org.adaikiss.xun.designpattern.gof.behavioral.visitor;


/**
 * @author hlw
 *
 */
public class Cashier implements IVisitor {

	private double count;

	public void settle(){
		System.out.println("total:" + count);
		count = 0;
	}

	@Override
	public void visit(Visitable visitable) {
		Goods g = (Goods)visitable;
		System.out.println(g);
		count += g.value();
	}

}
