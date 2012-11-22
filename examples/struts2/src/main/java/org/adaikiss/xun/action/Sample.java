package org.adaikiss.xun.action;

import org.apache.struts2.convention.annotation.Action;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
@Action(interceptorRefs = {})
public class Sample extends ActionSupport {
	public String execute(){
		return SUCCESS;
	}
}
