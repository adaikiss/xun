package org.adaikiss.xun.cache.spring.repository;

import java.util.List;

import org.adaikiss.xun.cache.data.Store;
import org.adaikiss.xun.cache.entity.Book;
import org.adaikiss.xun.cache.entity.Category;
import org.adaikiss.xun.cache.spring.repository.aspectj.BookRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations = { "classpath:applicationContext.xml",
		"classpath:applicationContext-cache.xml" })
public class CacheTestCase extends AbstractJUnit4SpringContextTests {

	@Autowired
	BookRepository repo;

	@Before
	public void setUp() throws Exception {
		Store.init();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFindById() {
		Long id = 1l;
		Book b = repo.findById(id);
		// from cache
		b = repo.findById(id);
		Assert.assertEquals(1, b.getTimes());
	}

	@Test
	public void testFindByCategory() {
		Category c = Store.categories.get(1l);
		List<Book> list = repo.findByCategory(c);
		int expected = list.get(0).getTimes();
		// from cache
		list = repo.findByCategory(c);
		Assert.assertEquals(expected, list.get(0).getTimes());
	}

	@Test
	public void testSave() {
		Category c = Store.categories.get(1l);
		List<Book> list = repo.findByCategory(c);
		int expected = list.get(0).getTimes();
		Book b = new Book(Store.bookIdentity.incrementAndGet(), "test",
				"3918321231", Store.categories.get(1l), "for test");
		// evict cache
		repo.save(b);
		list = repo.findByCategory(c);
		Assert.assertEquals(expected + 1, list.get(0).getTimes());
	}

	@Test
	public void testDelete() {
		Long id = 1l;
		Book b = repo.findById(id);
		Assert.assertNotNull(b);
		repo.delete(b);
		b = repo.findById(id);
		Assert.assertNull(b);
	}

}
