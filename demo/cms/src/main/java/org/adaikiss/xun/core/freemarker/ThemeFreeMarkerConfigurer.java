/**
 * 
 */
package org.adaikiss.xun.core.freemarker;

import java.io.File;
import java.io.IOException;

import org.adaikiss.xun.core.freemarker.templateloader.ThemeSpringTemplateLoader;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.cache.FileTemplateLoader;
import freemarker.cache.TemplateLoader;

/**
 * @author HuLingwei
 *
 */
public class ThemeFreeMarkerConfigurer extends FreeMarkerConfigurer {
	@Override
	protected TemplateLoader getTemplateLoaderForPath(String templateLoaderPath) {
		//copied from super class, just replace SpringTemplateLoader to ThemeSpringTemplateLoader
		if (isPreferFileSystemAccess()) {
			// Try to load via the file system, fall back to SpringTemplateLoader
			// (for hot detection of template changes, if possible).
			try {
				Resource path = getResourceLoader().getResource(templateLoaderPath);
				File file = path.getFile();  // will fail if not resolvable in the file system
				if (logger.isDebugEnabled()) {
					logger.debug(
							"Template loader path [" + path + "] resolved to file path [" + file.getAbsolutePath() + "]");
				}
				return new FileTemplateLoader(file);
			}
			catch (IOException ex) {
				if (logger.isDebugEnabled()) {
					logger.debug("Cannot resolve template loader path [" + templateLoaderPath +
							"] to [java.io.File]: using SpringTemplateLoader as fallback", ex);
				}
				return new ThemeSpringTemplateLoader(getResourceLoader(), templateLoaderPath);
			}
		}
		else {
			// Always load via SpringTemplateLoader (without hot detection of template changes).
			logger.debug("File system access not preferred: using SpringTemplateLoader");
			return new ThemeSpringTemplateLoader(getResourceLoader(), templateLoaderPath);
		}
	}
}
