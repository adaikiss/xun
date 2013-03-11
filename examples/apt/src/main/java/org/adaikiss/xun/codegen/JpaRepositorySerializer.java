/**
 * 
 */
package org.adaikiss.xun.codegen;

import java.io.Writer;
import java.util.regex.Pattern;

/**
 * @author hlw
 *
 */
public class JpaRepositorySerializer extends Serializer{

	/**
	 * 模板文件名称
	 */
	public static final String TPL_FILE = "repository.tpl";

	@Override
	public void serialize(Model model, Writer writer) {
		ClassModel cm = (ClassModel)model;
		String tpl = getTemplate();
		Pattern pattern = Pattern.compile("\\$\\{[a-zA-Z]+\\}", Pattern.MULTILINE);
		try {
			StringBuffer sb = replace(tpl, pattern);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	String getTemplateFileName() {
		return TPL_FILE;
	}

	public static void main(String[] args){
		System.out.println(new JpaRepositorySerializer().getTemplate());
	}
}
