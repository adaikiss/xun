/**
 * 
 */
package org.adaikiss.kay.trys;

/**
 * @author hlw
 *
 */
public class ClassTest {

	public ClassTest test(){
		return ClassTest.this;
	}

	public static void main(String[] args){
		System.out.println(ClassTest.class.getCanonicalName());
		System.out.println(ClassTest.class.getName());
		System.out.println(ClassTest.class.getSimpleName());
		System.out.println(ClassTest.class.getPackage());
	}
}
