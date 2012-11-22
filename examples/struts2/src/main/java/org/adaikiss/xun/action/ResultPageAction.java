package org.adaikiss.xun.action;


import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
//@Result(type = "freemarker")
public class ResultPageAction extends ActionSupport {
	private String message;
	public String jsp(){
		message = "Hello, World!";
		return "jsp";
	}

	public String freemarker(){
		message = "Hello, World!";
		return "freemarker";
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
