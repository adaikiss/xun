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
@WebFilter(asyncSupported = true, filterName = "HomeFilter", urlPatterns = {"/*"})
public class HomeFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		String requestURI = req.getRequestURI();
		if("/".equals(requestURI)){
			request.getRequestDispatcher("/index.html").forward(request, response);
			return;
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}

}
