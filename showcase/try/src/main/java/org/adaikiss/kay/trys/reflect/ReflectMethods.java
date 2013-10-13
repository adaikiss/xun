/**
 * @date 2011-8-22
 */
package org.adaikiss.kay.trys.reflect;

import java.lang.reflect.Method;

/**
 * @author hlw
 *
 */
public class ReflectMethods {

	public void test(Integer i){
		System.out.println("Integer " + i);
	}

	public void test(int i){
		System.out.println("int " + i);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		ReflectMethods r = new ReflectMethods();
		Method method1 = ReflectMethods.class.getDeclaredMethod("test", Integer.class);
		Method method2 = ReflectMethods.class.getDeclaredMethod("test", int.class);
		method1.invoke(r, 1);
		method2.invoke(r, 1);
		r.test(1);
	}

}
