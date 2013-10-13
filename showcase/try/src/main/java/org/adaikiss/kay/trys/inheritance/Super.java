package org.adaikiss.kay.trys.inheritance;

@TestInherit
public class Super {
	protected String message = "hello";

	public void say(){
		System.out.println(message);
	}

	protected boolean isAnnotationPresent(){
		return this.getClass().isAnnotationPresent(TestInherit.class);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
