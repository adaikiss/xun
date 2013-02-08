/**
 * 
 */
package org.adaikiss.xun.web;

import javax.servlet.http.HttpServletResponse;

/**
 * @author hlw
 *
 */
public interface Ajaxable{
	/**
	 * 是否为ajax请求
	 * @return
	 */
	public boolean isAjax();

	/**
	 * 获取httpServletResponse用来输出ajax
	 * @return
	 */
	public HttpServletResponse getResponse();
}
