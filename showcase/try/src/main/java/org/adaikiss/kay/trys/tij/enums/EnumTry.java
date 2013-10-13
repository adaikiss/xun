/**
 * 下午04:46:16
 */
package org.adaikiss.kay.trys.tij.enums;

import java.util.EnumSet;

/**
 * hlw
 * 
 */
public class EnumTry {

	public enum Fruit {
		Apple("苹果"), Banana("香蕉"), Pear("梨"), Watermelon("西瓜");
		private String description;

		private Fruit(String description) {
			this.description = description;
		}

		public String getDescription() {
			return description;
		}
	}

	enum Big { A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10,
		A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21,
		A22, A23, A24, A25, A26, A27, A28, A29, A30, A31, A32,
		A33, A34, A35, A36, A37, A38, A39, A40, A41, A42, A43,
		A44, A45, A46, A47, A48, A49, A50, A51, A52, A53, A54,
		A55, A56, A57, A58, A59, A60, A61, A62, A63, A64, A65,
		A66, A67, A68, A69, A70, A71, A72, A73, A74, A75 }

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EnumSet<Big> bigEnumSet = EnumSet.allOf(Big.class);
		System.out.println(bigEnumSet);
		for(Fruit fruit:Fruit.values()){
			System.out.println(fruit + ": " + fruit.getDescription());
		}
		System.out.println(Fruit.Apple.equals(Fruit.Apple));
	}

}
