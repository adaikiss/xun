package org.adaikiss.xun.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
@Namespace("/path")
public class Path extends ActionSupport {
	@Actions({@Action("/absolute"), @Action("relative")})
	public String one(){
		return "one";
	}
}
