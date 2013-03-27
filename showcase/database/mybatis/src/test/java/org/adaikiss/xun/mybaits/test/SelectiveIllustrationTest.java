/**
 * 
 */
package org.adaikiss.xun.mybaits.test;

import java.util.Date;

import junit.framework.Assert;

import org.adaikiss.xun.mybatis.entity.Noo;
import org.adaikiss.xun.mybatis.entity.NooMapper;
import org.adaikiss.xun.mybatis.test.MyBatisTestCase;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

/**
 * insert/insert-selective, update/update-selective
 * @author hlw
 *
 */
public class SelectiveIllustrationTest extends MyBatisTestCase {
	@Test
	public void testInsert(){
		SqlSession session = sqlSessionFactory.openSession();
		NooMapper mapper = session.getMapper(NooMapper.class);
		Noo entity = new Noo("Noo");
		mapper.insert(entity);
		session.commit();
		session.close();
		mapper = sqlSessionFactory.openSession().getMapper(NooMapper.class);
		entity = mapper.selectById(entity.getId());
		Assert.assertNull("should be inserted value null!", entity.getSize());
		Assert.assertNull(entity.getCreateTime());
	}

	@Test
	public void testInsertSelective(){
		SqlSession session = sqlSessionFactory.openSession();
		NooMapper mapper = session.getMapper(NooMapper.class);
		Noo entity = new Noo("Noo");
		mapper.insertSelective(entity);
		session.commit();
		session.close();
		mapper = sqlSessionFactory.openSession().getMapper(NooMapper.class);
		entity = mapper.selectById(entity.getId());
		Assert.assertEquals("should be default value 200!", new Integer(200), entity.getSize());
		Assert.assertNull(entity.getCreateTime());
	}

	@Test
	public void testUpdateById(){
		SqlSession session = sqlSessionFactory.openSession();
		NooMapper mapper = session.getMapper(NooMapper.class);
		Noo entity = new Noo("Noo", new Date());
		mapper.insert(entity);
		session.commit();
		Noo another = new Noo("another", null);
		another.setId(entity.getId());
		mapper.update(another);
		session.commit();
		session.close();
		mapper = sqlSessionFactory.openSession().getMapper(NooMapper.class);
		entity = mapper.selectById(entity.getId());
		Assert.assertEquals("another", entity.getName());
		Assert.assertNull(entity.getCreateTime());
	}

	@Test
	public void testUpdateByIdSelective(){
		SqlSession session = sqlSessionFactory.openSession();
		NooMapper mapper = session.getMapper(NooMapper.class);
		Noo entity = new Noo("Noo", new Date());
		mapper.insert(entity);
		session.commit();
		Noo another = new Noo("another", null);
		another.setId(entity.getId());
		mapper.updateByIdSelective(another);
		session.commit();
		session.close();
		mapper = sqlSessionFactory.openSession().getMapper(NooMapper.class);
		entity = mapper.selectById(entity.getId());
		Assert.assertEquals("another", entity.getName());
		Assert.assertNotNull(entity.getCreateTime());
	}
}
