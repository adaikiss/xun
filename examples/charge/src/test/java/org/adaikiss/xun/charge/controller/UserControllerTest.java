package org.adaikiss.xun.charge.controller;

import static org.springframework.test.web.server.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.server.setup.MockMvcBuilders.webApplicationContextSetup;

import org.adaikiss.xun.charge.test.AbstractContextControllerTests;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.server.MockMvc;
import org.springframework.test.web.server.MvcResult;

@ActiveProfiles({ "test", "jpa" })
@ContextConfiguration(locations = {
		"classpath:applicationContext.xml",
		"classpath:applicationContext-da.xml",
		"classpath:applicationContext-ds.xml",
		"classpath:applicationContext-tx.xml",
		"file:src/main/webapp/WEB-INF/springMVC-servlet.xml"})
public class UserControllerTest extends AbstractContextControllerTests{

	private static String URI = "/user/{id}";

	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = webApplicationContextSetup(this.wac).alwaysExpect(status().isOk()).build();
	}

	private static String XML =
			"<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" +
			"<user><foo>bar</foo><fruit>apple</fruit></user>";

	@Test
	public void testOne_Xml() throws Exception{
		this.mockMvc.perform(get(URI, "1.xml").accept(MediaType.APPLICATION_XML))
				.andExpect(content().xml(XML));
	}

	@Test
	public void testOne_Json() throws Exception{
		this.mockMvc.perform(get(URI, "1.json").accept(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.loginName").value("jetty"));
	}

	//@Test
	public void testAll_Xml() throws Exception{
		MvcResult result = this.mockMvc.perform(get(URI, "list.xml").accept(MediaType.APPLICATION_XML))
		.andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}

}
