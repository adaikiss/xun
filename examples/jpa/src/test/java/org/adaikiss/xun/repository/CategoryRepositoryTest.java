package org.adaikiss.xun.repository;

import static org.adaikiss.xun.specification.CategorySpecifications.topLevelAndDescriptionLongerThan;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.data.jpa.domain.Specifications.where;

import java.util.ArrayList;
import java.util.List;

import org.adaikiss.xun.entity.Category;
import org.adaikiss.xun.specification.BaseSpecifications;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springside.modules.orm.PropertyFilter;

@ActiveProfiles({ "test", "jpa" })
@ContextConfiguration(locations = { "classpath:applicationContext.xml",
		"classpath:applicationContext-da.xml",
		"classpath:applicationContext-ds.xml",
		"classpath:applicationContext-tx.xml" })
public class CategoryRepositoryTest extends
		AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	CategoryRepository categoryRepository;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFindCategoriesBySpecification() {
		Category category = categoryRepository.findOne(1l);
		List<Category> list = categoryRepository.findAll(
				where(topLevelAndDescriptionLongerThan(10)), new Sort(
						new Order(Direction.DESC, "id")));
		assertThat(list, hasItem(category));
	}

	@Test
	public void testFindCategoriesByPropertyFiltersSpecification(){
		Category category = categoryRepository.findOne(2l);
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		filters.add(new PropertyFilter("LIKES_description_OR_name", "%javaee%"));
		filters.add(new PropertyFilter("EQL_parent.id", 1l));
		Specification<Category> s = BaseSpecifications.propertyFilter(filters);
		List<Category> list = categoryRepository.findAll(where(s));
		assertThat(list, hasItem(category));
	}
}
