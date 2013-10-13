package org.adaikiss.kay.trys;

public class ForTry {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		for(int i = 0; i < 10 && i != 3; i++){
			System.out.println(i);
		}
		for(int i = 0; i < 100; i ++){
			System.out.println(i);
			if(i == 4){
				i = i + 4;
			}
		}
	}

}
