/**
 * 
 */
package org.adaikiss.kay.trys.tools;

import java.io.File;

import org.apache.commons.io.FileUtils;

/**
 * @author hlw
 *
 */
public class KillSvn {
	public static final String NAME = ".svn";
	public static void killSvn(File base) throws Exception{
		if(null == base || !base.exists() || !base.isDirectory()){
			return;
		}
		String name = base.getName();
		if(NAME.equalsIgnoreCase(name)){
			FileUtils.deleteDirectory(base);
			System.out.println(name);
			return;
		}
		File[] files = base.listFiles();
		for(File f : files){
			killSvn(f);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		killSvn(new File("D:\\zonesea\\wzh\\zj_bhps"));
	}

}
