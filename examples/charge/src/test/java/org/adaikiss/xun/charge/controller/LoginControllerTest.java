package org.adaikiss.xun.charge.controller;

import static org.junit.Assert.fail;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.post;
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

@ActiveProfiles({ "test", "jpa" })
@ContextConfiguration(locations = { "classpath:applicationContext.xml", "classpath:applicationContext-da.xml",
		"classpath:applicationContext-ds.xml", "classpath:applicationContext-tx.xml",
		"classpath:applicationContext-shiro.xml", "file:src/main/webapp/WEB-INF/springMVC-servlet.xml" })
public class LoginControllerTest extends AbstractContextControllerTests {

	private static String URI = "/login";

	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = webApplicationContextSetup(this.wac).alwaysExpect(status().isOk()).build();
	}

	@Test
	public void testLogin() throws Exception {
		this.mockMvc.perform(
				post(URI).param("username", "admin").param("password", "123456").accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.sucess").value(true));
	}

	@Test
	public void testLogout() {
		fail("Not yet implemented");
	}

}
