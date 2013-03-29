/**
 * 
 */
package org.adaikiss.xun.designpattern.gof.structural.facade;

/**
 * @author hlw
 * 
 */
public abstract class Department {
	public abstract void talk();

	public static void talk(Business business) {
		switch (business) {
		case Consulting:
			new CustomerServiceDepartment().talk();
			break;
		case Negotiating:
			new MarketDepartment().talk();
			break;
		case TechnicalSupport:
			new TechnologyServiceDepartment().talk();
			break;
		}
	}
}
