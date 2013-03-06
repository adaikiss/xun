/**
 * 
 */
package org.adaikiss.xun.servlet3.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * @author hlw
 *
 */
@WebFilter(asyncSupported = true, filterName = "BasePathFilter", urlPatterns = {"/*"})
public class BasePathFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		String base = ((HttpServletRequest)request).getContextPath();
		request.setAttribute("base", base);
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		
	}

}
