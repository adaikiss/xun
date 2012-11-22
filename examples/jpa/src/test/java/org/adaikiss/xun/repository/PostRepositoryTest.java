package org.adaikiss.xun.repository;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import org.adaikiss.xun.entity.Post;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

@ActiveProfiles({ "test", "jpa" })
@ContextConfiguration(locations = { "classpath:applicationContext.xml",
		"classpath:applicationContext-da.xml",
		"classpath:applicationContext-ds.xml",
		"classpath:applicationContext-tx.xml",
		"classpath:applicationContext-solr.xml"})
public class PostRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests{
	@Autowired
	PostRepository postRepository;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSolr(){
		Post post = new Post();
		post.setTitle("hello world!");
		post.setContent("Hello world! This is a spring&solr sample!");
		Post result = postRepository.save(post);
		Post post2 = new Post();
		post2.setTitle("roam");
		post2.setContent("Test the lucence query syntax!");
		Post result2 = postRepository.save(post2);
		postRepository.solrSave(result);
		postRepository.solrSave(result2);
		List<Post> list = postRepository.solrQuery("title_s:soam~");
		assertThat(list, hasItem(result2));
	}
}
