/**
 * 
 */
package org.adaikiss.xun.cache.spring.repository.proxy;

import static org.adaikiss.xun.cache.data.Store.bookIdentity;
import static org.adaikiss.xun.cache.data.Store.books;

import java.util.ArrayList;
import java.util.List;

import org.adaikiss.xun.cache.entity.Book;
import org.adaikiss.xun.cache.entity.Category;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Component;

/**
 * @author HuLingwei
 *
 */
@Component
public class BookRepositoryImpl implements BookRepository {
	@Cacheable(value = "book")
	@Override
	public Book findById(Long id) {
		Book b =  books.get(id);
		b.increaseTimes();
		return b;
	}

	@Cacheable(value = "books")
	@Override
	public List<Book> findByCategory(Category category) {
		List<Book> list = new ArrayList<Book>();
		for (Book b : books.values()) {
			if (category.equals(b.getCategory())) {
				list.add(b);
				b.increaseTimes();
			}
		}
		return list;
	}

	@CacheEvict(value = "books", allEntries = true)
	@Override
	public Book save(Book book) {
		Long id = bookIdentity.incrementAndGet();
		book.setId(id);
		books.put(id, book);
		return book;
	}

	@Caching(evict = { @CacheEvict("books"), @CacheEvict(value = "book") })
	@Override
	public void delete(Book book) {
		books.remove(book.getId());
	}
}
