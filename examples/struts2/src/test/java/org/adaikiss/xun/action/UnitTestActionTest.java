/**
 * 
 */
package org.adaikiss.xun.action;

import org.apache.struts2.StrutsSpringTestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockPageContext;
import org.springframework.mock.web.MockServletContext;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author hlw
 * 
 */
public class UnitTestActionTest extends StrutsSpringTestCase {


	protected void initServletMockObjects() {
		super.initServletMockObjects();
		String basePath = UnitTestAction.class.getResource("/").getPath();
		basePath = "file:" + basePath.substring(0, basePath.indexOf("target/")) + "src/main/webapp";
        servletContext = new MockServletContext(basePath, new FileSystemResourceLoader());
    }

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * Test method for {@link org.adaikiss.xun.action.UnitTestAction#showPage()}
	 * .
	 */
	@Test
	public void testShowPage() throws Exception{
		String name = "Jetty";
		ActionProxy proxy = null;
		UnitTestAction action = null;
		request.setParameter("name", name);
		proxy = getActionProxy("/unittest/page.action");
		action = (UnitTestAction) proxy.getAction();
		String result = proxy.execute();
		assertEquals(ActionSupport.SUCCESS, result);
		assertEquals(name, action.getName());
	}

	/**
	 * Test method for {@link org.adaikiss.xun.action.UnitTestAction#showVoid()}
	 * .
	 */
	@Test
	public void testShowVoid() throws Exception{
		String name = "Jetty";
		ActionProxy proxy = null;
		UnitTestAction action = null;
		request.setParameter("name", name);
		proxy = getActionProxy("/unittest/void.action");
		action = (UnitTestAction) proxy.getAction();
		proxy.execute();
		//String resultText = executeAction("/unittest/void.action");
		String resultText = response.getContentAsString();
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(resultText);
		JsonNode json = mapper.readTree(resultText);
		assertEquals(true, json.get("success").asBoolean());
		assertEquals(name, action.getName());
	}

}
