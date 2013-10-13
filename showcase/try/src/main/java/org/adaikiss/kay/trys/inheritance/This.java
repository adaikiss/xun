package org.adaikiss.kay.trys.inheritance;

public class This extends Super{
	public String message = "hi";
	public void say(){
		super.message = message;
		super.say();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new This().say();
		System.out.println(new This().isAnnotationPresent());
	}

}
