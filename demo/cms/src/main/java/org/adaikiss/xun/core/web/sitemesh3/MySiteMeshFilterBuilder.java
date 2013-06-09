package org.adaikiss.xun.core.web.sitemesh3;

import java.util.List;

import org.sitemesh.DecoratorSelector;
import org.sitemesh.builder.SiteMeshFilterBuilder;

/**
 * 继承两个方法处理setCustomDecoratorSelector使addDecoratorPath无效的问题
 * @author hlw
 *
 */
public class MySiteMeshFilterBuilder extends SiteMeshFilterBuilder {

	/**
	 * Add multiple decorator paths to be used for a specific content path. Use
	 * this to apply multiple decorators to a single page.
	 * 
	 * <p>
	 * Note: If {@link #setCustomDecoratorSelector(DecoratorSelector)} is
	 * called, any decorator paths are ignored, as they are only used by the
	 * default DecoratorSelector implementation.
	 * </p>
	 */
	public SiteMeshFilterBuilder addDecoratorPaths(String contentPath, String... decoratorPaths) {
		((RequestParamAndPathBasedDecoratorSelector) getDecoratorSelector()).put(contentPath, decoratorPaths);
		return self();
	}

	/**
	 * Add multiple decorator paths to be used for a specific content path. Use
	 * this to apply multiple decorators to a single page.
	 * 
	 * <p>
	 * Note: If {@link #setCustomDecoratorSelector(DecoratorSelector)} is
	 * called, any decorator paths are ignored, as they are only used by the
	 * default DecoratorSelector implementation.
	 * </p>
	 */
	public SiteMeshFilterBuilder addDecoratorPaths(String contentPath, List<String> decoratorPaths) {
		((RequestParamAndPathBasedDecoratorSelector) getDecoratorSelector()).put(contentPath,
				decoratorPaths.toArray(new String[decoratorPaths.size()]));
		return self();
	}
}
