/**
 * 
 */
package org.adaikiss.xun.freemarker;

import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

/**
 * expanded freemarker view resolver
 * @author hlw
 * 
 */
public class RichFreeMarkerViewResolver extends FreeMarkerViewResolver {

	@Override
	@SuppressWarnings("rawtypes")
	protected Class requiredViewClass() {
		return RichFreeMarkerView.class;
	}

}
