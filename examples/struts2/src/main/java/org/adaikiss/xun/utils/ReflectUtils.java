/**
 * 
 */
package org.adaikiss.xun.utils;

/**
 * @author hlw
 *
 */
public class ReflectUtils {
	/**
	 * return the class or throw an Exception
	 * @param packageName
	 * @param className
	 * @return
	 */
	public static Class<?> getClass(String packageName, String className){
		try {
			return Class.forName(packageName + "." + className);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
}
