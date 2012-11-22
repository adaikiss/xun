/**
 * 
 */
package org.adaikiss.xun.security;

import junit.framework.Assert;

import org.adaikiss.xun.serialization.msgpack.SimplePrincipalCollectionTemplate;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.junit.Before;
import org.junit.Test;
import org.msgpack.MessagePack;
import org.msgpack.type.Value;

/**
 * @author hlw
 *
 */
public class SimplePrincipalCollectionTemplateTest {

	private MessagePack msgPack;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		msgPack = new MessagePack();
		msgPack.register(SimplePrincipalCollection.class, new SimplePrincipalCollectionTemplate());
	}

	@Test
	public void test() throws Exception{
		SimplePrincipalCollection principals = new SimplePrincipalCollection("admin", "realm");
		byte[] packed = msgPack.write(principals);
		Value unpacked = msgPack.read(packed);
		principals = msgPack.convert(unpacked, SimplePrincipalCollection.class);
		Assert.assertEquals(1, principals.fromRealm("realm").size());
	}

}
