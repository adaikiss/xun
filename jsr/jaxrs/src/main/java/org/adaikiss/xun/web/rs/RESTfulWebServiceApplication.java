package org.adaikiss.xun.web.rs;

import javax.ws.rs.ApplicationPath;

import com.sun.jersey.api.core.PackagesResourceConfig;

@ApplicationPath("/rs")
public class RESTfulWebServiceApplication extends PackagesResourceConfig {
	public RESTfulWebServiceApplication(){
		super("org.adaikiss.xun.web.rs");
	}
}
