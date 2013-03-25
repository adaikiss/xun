/**
 * 
 */
package org.adaikiss.xun.mybaits.test;

import junit.framework.Assert;

import org.adaikiss.xun.mybatis.entity.Noo;
import org.adaikiss.xun.mybatis.entity.NooMapper;
import org.adaikiss.xun.mybatis.test.MyBatisTestCase;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

/**
 * @author hlw
 * 
 */
public class XmlIllustrationTest extends MyBatisTestCase {
	@Test
	public void testBasicUsage() {
		SqlSession session = sqlSessionFactory.openSession();
		NooMapper mapper = session.getMapper(NooMapper.class);
		Noo entity = new Noo("noo");

		mapper.add(entity);
		Assert.assertNotNull(entity.getId());
		session.commit();
		session.close();

		session = sqlSessionFactory.openSession();
		mapper = session.getMapper(NooMapper.class);
		entity = mapper.get(entity.getId());
		Assert.assertEquals("noo", entity.getName());
		session.commit();
		session.close();

		session = sqlSessionFactory.openSession();
		mapper = session.getMapper(NooMapper.class);
		entity.setName("BigNoo");
		mapper.update(entity);
		session.commit();
		session.close();
		session = sqlSessionFactory.openSession();
		mapper = session.getMapper(NooMapper.class);
		entity = mapper.get(entity.getId());
		Assert.assertEquals("BigNoo", entity.getName());
		session.commit();
		session.close();

		session = sqlSessionFactory.openSession();
		mapper = session.getMapper(NooMapper.class);
		mapper.remove(entity.getId());
		session.commit();
		session.close();
		session = sqlSessionFactory.openSession();
		mapper = session.getMapper(NooMapper.class);
		Assert.assertNull(mapper.get(entity.getId()));
		session.commit();
		session.close();
	}
}
