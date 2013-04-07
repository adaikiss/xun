/**
 * 
 */
package org.adaikiss.xun.designpattern.gof.behavioral.visitor;

/**
 * @author hlw
 * 
 */
public class Goods implements Visitable {

	private String name;

	private double price;

	private double amount;

	public Goods(String name, double price, double amount) {
		super();
		this.name = name;
		this.price = price;
		this.amount = amount;
	}

	public double value() {
		return price * amount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public void accept(IVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public String toString(){
		return name + " " + amount + "x" + price;
	}
}
