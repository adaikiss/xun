/**
 * 
 */
package org.adaikiss.xun.freemarker;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
	}

}
