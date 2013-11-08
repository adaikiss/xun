/**
 * 
 */
package org.adaikiss.xun.cache.spring.repository.aspectj;

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
@Cacheable(value = "aa")
public class BookRepository {

	@Cacheable(value = "book", key = "#id")
	public Book findById(Long id) {
		Book b =  books.get(id);
		if(null != b){
			b.increaseTimes();
		}
		return b;
	}

	@Cacheable(value = "books", key = "#category.id")
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
	public Book save(Book book) {
		Long id = bookIdentity.incrementAndGet();
		book.setId(id);
		books.put(id, book);
		return book;
	}

	@Caching(evict = { @CacheEvict(value = "books", allEntries = true), @CacheEvict(value = "book", key = "#book.id") })
	public void delete(Book book) {
		books.remove(book.getId());
	}
}
