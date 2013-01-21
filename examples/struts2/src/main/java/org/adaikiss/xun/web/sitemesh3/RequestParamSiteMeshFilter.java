package org.adaikiss.xun.web.sitemesh3;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;
import org.sitemesh.config.ObjectFactory;
import org.sitemesh.config.properties.PropertiesFilterConfigurator;
import org.sitemesh.config.xml.XmlFilterConfigurator;


/**
 * 支持request.attribute/request.parameter方式决定是否装饰页面
 * @author hlw
 *
 */
public class RequestParamSiteMeshFilter extends ConfigurableSiteMeshFilter {
	private FilterConfig filterConfig;

	private Set<String> excludedParamNames;
	private String decoratorName = "decorator";

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		String excludedParamNameStr = filterConfig.getInitParameter("excludedParamNames");
		if (StringUtils.isBlank(excludedParamNameStr)) {
			return;
		}
		excludedParamNames = new HashSet<String>();
		for (String paramName : excludedParamNameStr.split(",")) {
			excludedParamNames.add(StringUtils.trim(paramName));
		}
		String decoratorName = filterConfig.getInitParameter("decoratorName");
		if (StringUtils.isNotBlank(decoratorName)) {
			this.decoratorName = decoratorName;
		}
		this.filterConfig = filterConfig;
		super.init(filterConfig);
	}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		if (exclude(servletRequest)) {
			filterChain.doFilter(servletRequest, servletResponse);
			return;
		}
		super.doFilter(servletRequest, servletResponse, filterChain);
	}

	@Override
	protected Filter setup() throws ServletException {
		ObjectFactory objectFactory = getObjectFactory();
		/************************/
		SiteMeshFilterBuilder builder = new MySiteMeshFilterBuilder();

		builder.setCustomDecoratorSelector(new RequestParamAndPathBasedDecoratorSelector(decoratorName));
		/************************/
		new PropertiesFilterConfigurator(objectFactory, getConfigProperties(filterConfig)).configureFilter(builder);

		new XmlFilterConfigurator(getObjectFactory(), loadConfigXml(filterConfig, getConfigFileName()))
				.configureFilter(builder);

		applyCustomConfiguration(builder);

		return builder.create();
	}

	/**
	 * 是否不修饰
	 * 
	 * @param servletRequest
	 * @return
	 */
	private boolean exclude(ServletRequest servletRequest) {
		for (String excludedParamName : excludedParamNames) {
			Object isExclude = servletRequest.getAttribute(excludedParamName);
			if (null != isExclude) {
				if (isExclude.getClass().equals(Boolean.class)) {
					return (Boolean) isExclude;
				}
				if (isExclude.getClass().equals(String.class)) {
					return Boolean.parseBoolean((String) isExclude);
				}
				return false;
			}
			String paramValue = servletRequest.getParameter(excludedParamName);
			if (!StringUtils.isBlank(paramValue) && Boolean.parseBoolean(paramValue)) {
				return true;
			}
		}
		return false;
	}
}
