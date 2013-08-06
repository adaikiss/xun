/**
 * 
 */
package org.adaikiss.xun.core.freemarker.templateloader;

import java.io.IOException;

import org.adaikiss.xun.core.util.Constant;
import org.adaikiss.xun.core.util.PreferenceHelper;
import org.springframework.core.io.ResourceLoader;
import org.springframework.ui.freemarker.SpringTemplateLoader;

/**
 * @author hlw
 *
 */
public class ThemeSpringTemplateLoader extends SpringTemplateLoader {
	private final String[] excludes;
	public ThemeSpringTemplateLoader(ResourceLoader resourceLoader, String templateLoaderPath, String...excludes){
		super(resourceLoader, templateLoaderPath);
		this.excludes = excludes;
	}

	@Override
	public Object findTemplateSource(String name) throws IOException {
		if(exclude(name)){
			return super.findTemplateSource(name);
		}
		//通过theme查找模板文件
		Object themed = super.findTemplateSource(PreferenceHelper.getPreference().getTheme() + "/" + name);
		if(null != themed){
			return themed;
		}
		//使用的就是默认的theme, 不再查询
		if(Constant.DEFAULT_THEME.equals(PreferenceHelper.getPreference().getTheme())){
			return null;
		}
		//不存在则查默认theme文件
		return super.findTemplateSource(Constant.DEFAULT_THEME + "/" + name);
	}

	private boolean exclude(String name){
		if(excludes.length == 0){
			return false;
		}
		for(String exclude : excludes){
			if(name.startsWith(exclude)){
				return true;
			}
		}
		return false;
	}
}
