/**
 * 
 */
package org.adaikiss.xun.servlet3.examples;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author hlw
 *
 */
public class MessageRepository<E> {
	private List<E> messages = new CopyOnWriteArrayList<E>();
	public void add(E msg){
		messages.add(msg);
	}

	public List<E> get(Integer index){
		if (this.messages.isEmpty()) {
			return Collections.<E> emptyList();
		}
		if((index < 0) || (index > this.messages.size())){
			throw new RuntimeException("Invalid message index");
		}
		return this.messages.subList(index, this.messages.size());
	}
}
