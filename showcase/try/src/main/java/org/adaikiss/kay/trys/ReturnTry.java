/**
 * 
 */
package org.adaikiss.kay.trys;

/**
 * @author Administrator
 *
 */
public class ReturnTry {
	
	public static int a(){
		int i = 1;
		try{
			System.out.println("try");
			return get(i);
		}finally{
			System.out.println("finally");
			i++;
		}
	}

	public static int b(){
		int i = 1;
		try{
			System.out.println("try");
			get(i);
		}finally{
			System.out.println("finally");
			i = 3;
		}
		System.out.println("here:" + i);
		return i;
	}

	public static int get(int i){
		System.out.println("get");
		return i;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(a());
		System.out.println(b());
	}

}
