package org.adaikiss.xun.web.rs;

import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

@Path("/hero")
public class Hero {
	@GET
	@Produces("text/plain")
	public String getHero() {
		return "Tiny";
	}

	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public void addHeroWithFile(@FormDataParam("properties") InputStream properties, 
			@FormDataParam("properties") FormDataContentDisposition fileDetail,
			@FormDataParam("name") String name){
		System.out.println(name);
	}

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void addHero(@FormParam("properties") String properties, 
			@FormParam("name") String name){
		System.out.println(name);
	}
}
