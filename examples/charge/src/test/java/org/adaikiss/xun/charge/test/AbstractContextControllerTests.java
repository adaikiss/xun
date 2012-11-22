/**
 * 
 */
package org.adaikiss.xun.charge.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author hlw
 * 
 */
@WebAppConfiguration
public class AbstractContextControllerTests extends
		AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	protected WebApplicationContext wac;

}