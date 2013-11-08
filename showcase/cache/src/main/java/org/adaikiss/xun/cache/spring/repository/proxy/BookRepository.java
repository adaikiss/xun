/**
 * 
 */
package org.adaikiss.xun.cache.spring.repository.proxy;

import java.util.List;

import org.adaikiss.xun.cache.entity.Book;
import org.adaikiss.xun.cache.entity.Category;

/**
 * @author HuLingwei
 *
 */
public interface BookRepository {
	public Book findById(Long id);

	public List<Book> findByCategory(Category category);

	public Book save(Book book);

	public void delete(Book book);
}
