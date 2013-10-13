/**
 * 2011-2-25
 */
package org.adaikiss.kay.trys;

/**
 * hlw
 *
 */
public class SwitchTry {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int i = 5;
		int j = 0;
		switch(i){
		case 1:{
			System.out.println(1);
		}
		case 2:{
			System.out.println(2);
			break;
		}
		case 5:{
			switch(j){
			case 0:{
				System.out.println(50);
				break;
			}
			case 1:{
				System.out.println(51);
				break;
			}
			}
		}
		case 3:{
			System.out.println(3);
		}
		case 4:{
			System.out.println(4);
			break;
		}
		case 6:{
			System.out.println(5);
			break;
		}
		}
	}

}
