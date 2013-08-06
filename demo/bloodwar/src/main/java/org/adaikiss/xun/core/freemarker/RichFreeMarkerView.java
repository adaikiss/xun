/**
 * 
 */
package org.adaikiss.xun.core.freemarker;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.adaikiss.xun.core.util.PreferenceHelper;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

/**
 * expanded freemarker view
 * @author hlw
 * 
 */
public class RichFreeMarkerView extends FreeMarkerView {

	@Override
	protected void exposeHelpers(Map<String, Object> model,
			HttpServletRequest request) throws Exception {
		super.exposeHelpers(model, request);
		//add base(Context Path) to model
		model.put("base", request.getContextPath());
		//add theme to model
		model.put("theme", PreferenceHelper.getPreference().getTheme());
	}

}
