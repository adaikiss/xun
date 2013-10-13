/**
 * 
 */
package org.adaikiss.kay.trys;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;

/**
 * @author hlw
 *
 */
public class Util {

	public static void checkMulti(String path) throws Exception{
		BufferedReader in = new BufferedReader(new FileReader(path));
		String l = null;
		String tmp = null;
		Set<String> set = new HashSet<String>();
		while((l = in.readLine()) != null){
			String[] n = l.split(",");
			for(String p : n){
				if(p.length() != 11){
					if(tmp != null){
						String ph = tmp + p;
						tmp = null;
						if(set.contains(ph)){
							System.out.println(ph);
						}else{							
							set.add(ph);
						}
					}else{
						tmp = p;
						continue;
					}
				}else{
					if(set.contains(p)){
						System.out.println(p);
					}else{
						set.add(p);
					}
				}
			}
		}
		in.close();
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		checkMulti("e:/phone.txt");
	}

}
