/**
 * 
 */
package org.adaikiss.xun;

/**
 * @author hlw
 *
 */
public class StringUtils {
	public static String join(Object...objs){
		StringBuilder sb = new StringBuilder();
		for(Object obj : objs){
			sb.append(obj);
		}
		return sb.toString();
	}
}
