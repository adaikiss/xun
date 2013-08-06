package org.adaikiss.xun.core.web.sitemesh3;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.sitemesh.DecoratorSelector;
import org.sitemesh.config.PathMapper;
import org.sitemesh.content.Content;
import org.sitemesh.webapp.WebAppContext;

public class RequestParamAndPathBasedDecoratorSelector implements DecoratorSelector<WebAppContext> {

	private static final String[] EMPTY = {};

	private final String selector;
	private final PathMapper<String[]> pathMapper = new PathMapper<String[]>();

	public RequestParamAndPathBasedDecoratorSelector(String selector) {
		this.selector = selector;
	}

	public RequestParamAndPathBasedDecoratorSelector put(String contentPath, String... decoratorPaths) {
		pathMapper.put(contentPath, decoratorPaths);
		return this;
	}

	@Override
	public String[] selectDecoratorPaths(Content content, WebAppContext context) throws IOException {

		String[] result = pathMapper.get(getPath(context));
		return result == null ? EMPTY : result;
	}

	/**
	 * 扩展request parameter优先的decorator选择方法
	 * 
	 * @param context
	 * @return
	 */
	private String getPath(WebAppContext context) {
		String decorator = (String) context.getRequest().getAttribute(selector);
		if (StringUtils.isNotBlank(decorator)) {
			return decorator;
		}
		decorator = context.getRequest().getParameter(selector);
		if (StringUtils.isNotBlank(decorator)) {
			return decorator;
		}
		return context.getPath();
	}
}
