/**
 * 
 */
package org.adaikiss.xun.mybaits.test;

import org.adaikiss.xun.mybatis.entity.Too;
import org.adaikiss.xun.mybatis.service.TooService;
import org.adaikiss.xun.mybatis.spring.AppConfig;
import org.junit.Assert;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author hlw
 *
 */
//@Configurable
//@ContextConfiguration(classes = {AppConfig.class}, loader = AnnotationConfigContextLoader.class)
public class SpringTransactionIllustrationTest{
	TooService service;

	JdbcTemplate jdbcTemplate;

	public void testSpringTransaction(){
		String selectSql = "select count(id) from xun_too";
		Too too1 = new Too("too");
		try {
			service.add(too1);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		Integer count = jdbcTemplate.queryForObject(selectSql, Integer.class);
		Assert.assertEquals(new Integer(1), count);
		Too too2 = new Too("rollback");
		try {
			service.add(too2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		count = jdbcTemplate.queryForObject(selectSql, Integer.class);
		Assert.assertEquals("should be only one row!", new Integer(1), count);
		
	}

	public static void main(String args[]){
		SpringTransactionIllustrationTest test = new SpringTransactionIllustrationTest();
		ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		test.service = ctx.getBean(TooService.class);
		test.jdbcTemplate = ctx.getBean(JdbcTemplate.class);;
		test.testSpringTransaction();
	}
}
