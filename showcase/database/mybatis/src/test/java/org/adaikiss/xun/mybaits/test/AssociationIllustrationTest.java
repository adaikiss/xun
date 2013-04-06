/**
 * 
 */
package org.adaikiss.xun.mybaits.test;

import java.util.Date;

import org.adaikiss.xun.mybatis.entity.Aoo;
import org.adaikiss.xun.mybatis.entity.AooMapper;
import org.adaikiss.xun.mybatis.entity.Boo;
import org.adaikiss.xun.mybatis.entity.BooMapper;
import org.adaikiss.xun.mybatis.entity.Coo;
import org.adaikiss.xun.mybatis.entity.CooMapper;
import org.adaikiss.xun.mybatis.entity.Foo;
import org.adaikiss.xun.mybatis.entity.FooMapper;
import org.adaikiss.xun.mybatis.entity.MiddleTableMapper;
import org.adaikiss.xun.mybatis.test.MyBatisTestCase;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

/**
 * one-to-one, one-to-many, many-to-many
 * 
 * @author hlw
 * 
 */
public class AssociationIllustrationTest extends MyBatisTestCase {

	private Foo insert() {
		sqlSessionFactory.getConfiguration().addMapper(AooMapper.class);
		sqlSessionFactory.getConfiguration().addMapper(BooMapper.class);
		sqlSessionFactory.getConfiguration().addMapper(CooMapper.class);
		sqlSessionFactory.getConfiguration().addMapper(MiddleTableMapper.class);
		sqlSessionFactory.getConfiguration().addMapper(FooMapper.class);
		SqlSession session = sqlSessionFactory.openSession();
		AooMapper aooMapper = session.getMapper(AooMapper.class);
		BooMapper booMapper = session.getMapper(BooMapper.class);
		CooMapper cooMapper = session.getMapper(CooMapper.class);
		MiddleTableMapper middleTableMapper = session
				.getMapper(MiddleTableMapper.class);
		FooMapper fooMapper = session.getMapper(FooMapper.class);
		Aoo aoo = new Aoo("aoo", new Date());
		aooMapper.insert(aoo);
		session.commit();
		Foo foo = new Foo("foo");
		foo.setAoo(aoo);
		fooMapper.insert(foo);
		session.commit();
		Boo boo1 = new Boo("boo1");
		Boo boo2 = new Boo("boo2");
		boo1.setFooId(foo.getId());
		boo2.setFooId(foo.getId());
		booMapper.insert(boo1);
		booMapper.insert(boo2);
		session.commit();
		Coo coo1 = new Coo("coo1");
		Coo coo2 = new Coo("coo2");
		cooMapper.insert(coo1);
		cooMapper.insert(coo2);
		session.commit();
		middleTableMapper.insertFooCoo(foo.getId(), coo1.getId());
		middleTableMapper.insertFooCoo(foo.getId(), coo2.getId());
		session.commit();
		session.close();
		return foo;
	}

	@Test
	public void testSelectById() {
		Foo foo = insert();
		SqlSession session = sqlSessionFactory.openSession();
		FooMapper mapper = session.getMapper(FooMapper.class);
		Foo entity = mapper.selectById(foo.getId());
		Assert.assertNotNull(entity);
		Assert.assertNull("should not be queried!", entity.getAoo());
		Assert.assertNull("should not be queried!", entity.getBoos());
	}

	@Test
	public void testSelectByIdWithAoo() {
		Foo foo = insert();
		SqlSession session = sqlSessionFactory.openSession();
		FooMapper mapper = session.getMapper(FooMapper.class);
		Foo entity = mapper.SelectByIdWithAoo(foo.getId());
		Assert.assertNotNull(entity);
		Assert.assertNotNull("should be queried!", entity.getAoo());
		Assert.assertNull("should not be queried!", entity.getBoos());
		Assert.assertNull("should not be queried!", entity.getCoos());
	}

	@Test
	public void testSelectByIdWithBoos() {
		Foo foo = insert();
		SqlSession session = sqlSessionFactory.openSession();
		FooMapper mapper = session.getMapper(FooMapper.class);
		Foo entity = mapper.SelectByIdWithBoos(foo.getId());
		Assert.assertNotNull(entity);
		Assert.assertNull("should not be queried!", entity.getAoo());
		Assert.assertNotNull("should be queried!", entity.getBoos());
		Assert.assertNull("should not be queried!", entity.getCoos());
		Assert.assertEquals(2, entity.getBoos().size());
	}

	@Test
	public void testSelectByIdWithCoos() {
		Foo foo = insert();
		SqlSession session = sqlSessionFactory.openSession();
		FooMapper mapper = session.getMapper(FooMapper.class);
		Foo entity = mapper.SelectByIdWithCoos(foo.getId());
		Assert.assertNotNull(entity);
		Assert.assertNull("should not be queried!", entity.getAoo());
		Assert.assertNull("should not be queried!", entity.getBoos());
		Assert.assertNotNull("should be queried!", entity.getCoos());
		Assert.assertEquals(2, entity.getCoos().size());
	}

	/**
	 * set lazyLoadingEnabled with true/false
	 */
	@Test
	public void testSelectByIdWithAllAssociations() {
		Foo foo = insert();
		SqlSession session = sqlSessionFactory.openSession();
		FooMapper mapper = session.getMapper(FooMapper.class);
		Foo entity = mapper.selectByIdWithAllAssociations(foo.getId());
		Assert.assertNotNull(entity);
		System.out.println("#########before read aoo############################");
		Assert.assertNotNull("should be queried!", entity.getAoo());
		System.out.println("#########after read aoo###########################");
		System.out.println("#########before read boos############################");
		Assert.assertNotNull("should be queried!", entity.getBoos());
		System.out.println("#########after read boos############################");
		Assert.assertEquals(2, entity.getBoos().size());
		System.out.println("#########after read boos size############################");
		System.out.println("#########before read coos############################");
		Assert.assertNotNull("should be queried!", entity.getCoos());
		System.out.println("#########after read coos############################");
		Assert.assertEquals(2, entity.getCoos().size());
		System.out.println("#########after read coos size############################");
	}
}
