/**
 * 
 */
package org.adaikiss.xun.mybaits.test;

import java.util.Date;

import junit.framework.Assert;

import org.adaikiss.xun.mybatis.entity.Aoo;
import org.adaikiss.xun.mybatis.entity.AooMapper;
import org.adaikiss.xun.mybatis.test.MyBatisTestCase;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

/**
 * @author hlw
 *
 */
public class AnnotationIllustrationTest extends MyBatisTestCase {
	@Test
	public void testBasicUsage(){
		sqlSessionFactory.getConfiguration().addMapper(AooMapper.class);
		SqlSession session = sqlSessionFactory.openSession();
		AooMapper mapper = session.getMapper(AooMapper.class);
		Aoo entity = new Aoo("aoo", new Date());

		mapper.insert(entity);
		Assert.assertNotNull(entity.getId());
		session.commit();
		session.close();

		session = sqlSessionFactory.openSession();
		mapper = session.getMapper(AooMapper.class);
		entity = mapper.selectById(entity.getId());
		Assert.assertEquals("aoo", entity.getName());
		session.commit();
		session.close();

		session = sqlSessionFactory.openSession();
		mapper = session.getMapper(AooMapper.class);
		entity.setName("BigNoo");
		mapper.update(entity);
		session.commit();
		session.close();
		session = sqlSessionFactory.openSession();
		mapper = session.getMapper(AooMapper.class);
		entity = mapper.selectById(entity.getId());
		Assert.assertEquals("BigNoo", entity.getName());
		session.commit();
		session.close();

		session = sqlSessionFactory.openSession();
		mapper = session.getMapper(AooMapper.class);
		mapper.delete(entity.getId());
		session.commit();
		session.close();
		session = sqlSessionFactory.openSession();
		mapper = session.getMapper(AooMapper.class);
		Assert.assertNull(mapper.selectById(entity.getId()));
		session.commit();
		session.close();
	}
}
