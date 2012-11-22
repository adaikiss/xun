/**
 * 
 */
package org.adaikiss.xun.security;

import junit.framework.Assert;

import org.adaikiss.xun.serialization.msgpack.SimplePrincipalCollectionTemplate;
import org.adaikiss.xun.serialization.msgpack.SimpleSessionTemplate;
import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.junit.Before;
import org.junit.Test;
import org.msgpack.MessagePack;
import org.msgpack.type.Value;

/**
 * @author hlw
 *
 */
public class SimpleSessionTemplateTest {

	private MessagePack msgPack;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		msgPack = new MessagePack();
		msgPack.register(SimpleSession.class, new SimpleSessionTemplate());
		msgPack.register(SimplePrincipalCollection.class, new SimplePrincipalCollectionTemplate());
	}

	@Test
	public void test() throws Exception{
		SimpleSession session = new SimpleSession("localhost");
		session.setId(1234567890l);
		session.setTimeout(100);
		String key = "test";
		String data = "测试!";
		session.setAttribute(key, data);
		session.setAttribute("principals", new SimplePrincipalCollection("admin", "realm"));
		byte[] packed = msgPack.write(session);
		Value unpacked = msgPack.read(packed);
		session = msgPack.convert(unpacked, SimpleSession.class);
		Assert.assertEquals(data, session.getAttribute(key));
	}

}
