/**
 * 
 */
package org.adaikiss.xun.mybaits.test;

import java.util.UUID;

import junit.framework.Assert;

import org.adaikiss.xun.mybatis.entity.Ioo;
import org.adaikiss.xun.mybatis.entity.IooMapper;
import org.adaikiss.xun.mybatis.test.MyBatisTestCase;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

/**
 * mybatis custom TypeHandler showcase
 * @author hlw
 *
 */
public class CustomTypeHandlerIllustrationTest extends MyBatisTestCase {
	@Test
	public void testUUIDTypeHandler(){
		sqlSessionFactory.getConfiguration().addMapper(IooMapper.class);
//		sqlSessionFactory.getConfiguration().addKeyGenerator("uuid", new UUIDKeyGenerator());
		SqlSession session = sqlSessionFactory.openSession();
		IooMapper mapper = session.getMapper(IooMapper.class);
		Ioo entity = new Ioo(UUID.randomUUID(), "ioo");
		mapper.insert(entity);
		session.commit();
		session.close();
		session = sqlSessionFactory.openSession();
		mapper = session.getMapper(IooMapper.class);
		entity = mapper.selectById(entity.getId());
		session.close();
		Assert.assertNotNull(entity);
	}
}
