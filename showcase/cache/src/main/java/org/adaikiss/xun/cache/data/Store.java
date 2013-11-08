/**
 * 
 */
package org.adaikiss.xun.cache.data;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.adaikiss.xun.cache.entity.Book;
import org.adaikiss.xun.cache.entity.Category;

/**
 * @author HuLingwei
 *
 */
public class Store {
	public static Map<Long, Category> categories;
	public static Map<Long, Book> books;
	public static AtomicLong bookIdentity;
	public static AtomicLong categoryIdentity;

	public static void init(){
		 bookIdentity = new AtomicLong(0);
		 categoryIdentity = new AtomicLong(0);
		categories = new HashMap<Long, Category>();
		Long categoryId = categoryIdentity.incrementAndGet();
		categories.put(categoryId, new Category(new Long(categoryId),
				"biography"));
		categoryId = categoryIdentity.incrementAndGet();
		categories.put(categoryId,
				new Category(new Long(categoryId), "fiction"));
		books = new HashMap<Long, Book>();
		Long booksId = bookIdentity.incrementAndGet();
		books.put(booksId, new Book(new Long(booksId), "Sexy Chou's Life",
				"438748", categories.get(1l),
				"Sexy Chou, Chinese name--'Zhou Shichun', a sexy porn guy..."));
	}
}
