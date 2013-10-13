/**
 * @date 2011-8-11
 */
package org.adaikiss.kay.trys;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @author hlw
 *
 */
public class ToStringTry {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] str = new String[]{"aa", "BB", "c"};
		System.out.println(str);
		Set<String> set = new HashSet<String>();
		set.add("aaa");
		set.add("bbb");
		set.add("ccc");
		System.out.println(set.toString());
		StringBuilder sb = new StringBuilder();
		Iterator<String> it = set.iterator();
		while(it.hasNext()){
			sb.append(it.next());
			if(it.hasNext())
				sb.append(",");
		}
		System.out.println(sb);
	}

}
