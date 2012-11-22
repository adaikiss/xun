/**
 * 
 */
package org.adaikiss.xun.charge.enums;

/**
 * 收支类型
 * @author hlw
 *
 */
public enum ChargeType {
	revenue("收入"),
	expense("支出");
	private String description;
	private ChargeType(String description){
		this.description = description;
	}

	@Override
	public String toString(){
		return description;
	}
}
