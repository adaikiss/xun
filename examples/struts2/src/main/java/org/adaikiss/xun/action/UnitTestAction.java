/**
 * 
 */
package org.adaikiss.xun.action;

import javax.servlet.http.HttpServletResponse;

import org.adaikiss.xun.web.AjaxResponse;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author hlw
 * 
 */
@SuppressWarnings("serial")
public class UnitTestAction extends ActionSupport implements
		ServletResponseAware {

	private HttpServletResponse response;

	private String name;

	@Action("/unittest/page")
	public String showPage() {
		if ("Jetty".equals(name)){
			return SUCCESS;
		}
		return ERROR;
	}

	@Action("/unittest/void")
	public void showVoid() {
		AjaxResponse res = new AjaxResponse();
		if ("Jetty".equals(name)) {
			res.setSuccess(true);
		} else {
			res.setMsg("Only Jetty is allowed!");
		}
		res.writeJson(response);
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
