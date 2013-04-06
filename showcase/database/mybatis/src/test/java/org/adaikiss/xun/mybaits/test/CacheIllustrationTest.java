/**
 * 
 */
package org.adaikiss.xun.mybaits.test;

import org.adaikiss.xun.mybatis.entity.Aoo;
import org.adaikiss.xun.mybatis.entity.AooMapper;
import org.adaikiss.xun.mybatis.entity.Eoo;
import org.adaikiss.xun.mybatis.entity.EooMapper;
import org.adaikiss.xun.mybatis.entity.Moo;
import org.adaikiss.xun.mybatis.entity.MooMapper;
import org.adaikiss.xun.mybatis.test.MyBatisTestCase;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

/**
 * mybatis no-cache/first-level-cache/second-level-cache/custom-second-level-cache showcase
 * @author hlw
 *
 */
public class CacheIllustrationTest extends MyBatisTestCase {

	/**
	 * selects cross sessions use no cache 
	 */
	@Test
	public void testNoCache(){
		sqlSessionFactory.getConfiguration().addMapper(AooMapper.class);
		SqlSession session = sqlSessionFactory.openSession();
		AooMapper mapper = session.getMapper(AooMapper.class);
		Aoo entity = new Aoo("aoo", null);
		System.out.println("##########before insert###################");
		mapper.insert(entity);
		System.out.println("##########after insert###################");
		session.commit();
		System.out.println("##########before first select###################");
		System.out.println("--------a sql query will be sent------");
		entity = mapper.selectById(entity.getId());
		System.out.println("##########after first select###################");
		session.close();
		session = sqlSessionFactory.openSession();
		mapper = session.getMapper(AooMapper.class);
		System.out.println("##########before second select###################");
		System.out.println("--------a sql query will be sent------");
		entity = mapper.selectById(entity.getId());
		System.out.println("##########after second select###################");
	}

	/**
	 * selects in single session use first-level-cache(session cache)
	 */
	@Test
	public void testFirstLevelCache(){
		sqlSessionFactory.getConfiguration().addMapper(AooMapper.class);
		SqlSession session = sqlSessionFactory.openSession();
		AooMapper mapper = session.getMapper(AooMapper.class);
		Aoo entity = new Aoo("aoo", null);
		System.out.println("##########before insert###################");
		mapper.insert(entity);
		System.out.println("##########after insert###################");
		session.commit();
		System.out.println("##########before first select###################");
		System.out.println("------a sql query will be sent------");
		entity = mapper.selectById(entity.getId());
		System.out.println("##########after first  select###################");
		System.out.println("##########before second select###################");
		System.out.println("------no sql query will be sent------");
		entity = mapper.selectById(entity.getId());
		System.out.println("##########after second select###################");
	}

	/**
	 * selects cross sessions use second-level-cache(ehcache here)
	 */
	@Test
	public void testSecondLevelCache(){
		sqlSessionFactory.getConfiguration().addMapper(EooMapper.class);
		SqlSession session = sqlSessionFactory.openSession();
		EooMapper mapper = session.getMapper(EooMapper.class);
		Eoo entity = new Eoo("eoo");
		System.out.println("##########before insert###################");
		mapper.insert(entity);
		System.out.println("##########after insert###################");
		session.commit();
		System.out.println("##########before first select###################");
		System.out.println("------a sql query will be sent------");
		entity = mapper.selectById(entity.getId());
		System.out.println("##########after first select###################");
		session.close();
		session = sqlSessionFactory.openSession();
		mapper = session.getMapper(EooMapper.class);
		System.out.println("##########before second select###################");
		System.out.println("------no sql query will be sent------");
		entity = mapper.selectById(entity.getId());
		System.out.println("##########after second select###################");
	}

	/**
	 * selects cross sessions use second-level-cache(memcachedcache here)
	 */
	@Test
	public void testMemcachedSecondLevelCache(){
		sqlSessionFactory.getConfiguration().addMapper(MooMapper.class);
		SqlSession session = sqlSessionFactory.openSession();
		MooMapper mapper = session.getMapper(MooMapper.class);
		Moo entity = new Moo("moo");
		System.out.println("##########before insert###################");
		mapper.insert(entity);
		System.out.println("##########after insert###################");
		session.commit();
		System.out.println("##########before first select###################");
		System.out.println("------a sql query will be sent------");
		entity = mapper.selectById(entity.getId());
		System.out.println("##########after first select###################");
		session.close();
		session = sqlSessionFactory.openSession();
		mapper = session.getMapper(MooMapper.class);
		System.out.println("##########before second select###################");
		System.out.println("------no sql query will be sent------");
		entity = mapper.selectById(entity.getId());
		System.out.println("##########after second select###################");
	}
}
